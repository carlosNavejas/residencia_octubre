package com.bolsadeideas.springboot.app.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Asignatura;
import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Item_acervo;
import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
@SessionAttributes("item")
@RequestMapping("/acervo")
public class AcervoController {
	
	/*
	 * Authentication authentication
	 * objeto por el cual obtenemos el usuario autenticado dentro del controlador
	 * reciviendolo como un tipo de variable  
	 * */
	
	
	
	@Autowired
	private ServiciosServiceImpl serviciosDao;

	@RequestMapping("{id}/registrar-acervo")
	public String formInventario(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash,Authentication authentication) {
		if (id < 1) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		Biblioteca biblioteca = serviciosDao.findByIdBiblioteca(id);
		if (biblioteca == null) {
			flash.addFlashAttribute("info", "Aun no ha registrado una biblioteca");
			return "redirect:/home";
		}
		ArrayList<Asignatura> listaAsignaturas = (ArrayList<Asignatura>) serviciosDao.findAllAsignatura();
		Asignatura asignatura = new Asignatura();
		asignatura.setAsignatura("otra");
		asignatura.setId_asignatura((long) -1);
		listaAsignaturas.add(asignatura);
		Item_acervo item = new Item_acervo();
		model.addAttribute("item", item);
		model.addAttribute("biblioteca", biblioteca);
		model.addAttribute("asignaturas", listaAsignaturas);
		model.addAttribute("titulo", "Registro de acervo");
		return "/Acervo/formAcervo";
	}

	/* Eliminar item acervo */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Item_acervo item = serviciosDao.finfItemAcervo(id);
			if (item == null) {
				flash.addFlashAttribute("error", "Ha ocurrido un error intentelo mas tarde");
				return "redirect:/home";
			}
			serviciosDao.deleteIntemInventario(id);
			flash.addFlashAttribute("success", "Se ha eliminado con éxito!");
			return "redirect:/acervo/" + item.getBiblioteca().getClave_biblioteca() + "/listar/";
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar!");
		return "redirect:/home";
	}

//Guardar item Acervo
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar_item_inventario(Item_acervo item,

			@RequestParam(name = "id_item", required = false) Long id_item_inventario,
			@RequestParam(name = "clave_biblioteca", required = true) Long clave_biblioteca,
			@RequestParam(name = "fecha", required = false) String fecha, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensaje = (item.getId_item() != null) ? "Se ha editado correctamente" : "Se ha registrado correctamente";

		String men2 = "" + item.getCantidad() + "  " + item.getAsignatura();

		if (clave_biblioteca > 0) {
			Biblioteca biblioteca = serviciosDao.findByIdBiblioteca(clave_biblioteca);
			if (biblioteca != null) {

				item.setBiblioteca(biblioteca);
				if (fecha.length()>0) {
					SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date fechaAntesMod = new Date();
					try {
						fechaAntesMod = dateformat2.parse(fecha);
						item.setFechaRegistro(fechaAntesMod);
					} catch (Exception e) {

					}
				}

				serviciosDao.saveItemAcervo(item);
				status.setComplete();
				flash.addFlashAttribute("info", mensaje + "\t" + men2);
				return "redirect:/home";
			}
		}

		flash.addFlashAttribute("error", "Ha ocurrido un error al registrar en el inventario, intentelo mas tarde");
		return "redirect:/home";
	}

	@RequestMapping(value = "/guardarAsignatura", method = RequestMethod.POST)
	public String guardar_Asignarura(

			@RequestParam(name = "clave_biblioteca", required = true) Long clave_biblioteca,
			@RequestParam(name = "asginaturaaa", required = true) String asginaturaaa, Model model,
			RedirectAttributes flash) {

		if (clave_biblioteca < 1) {
			flash.addFlashAttribute("error", "Ha ocurrido un error al agregar la asignatura");
			return "redirect:/home";
		}
		Asignatura asignatura = new Asignatura();
		asignatura.setAsignatura(asginaturaaa);
		flash.addFlashAttribute("success", "Se ha registrado la asignatura");
		serviciosDao.saveAsignatura(asignatura);

		return "redirect:/acervo/" + clave_biblioteca + "/registrar-acervo";

	}

	@RequestMapping("{id}/editar-acervo")
	public String formEditarInventario(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id < 1) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}

		Item_acervo item = serviciosDao.finfItemAcervo(id);
		if (item == null) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}

		Biblioteca bilioteca = item.getBiblioteca();

		ArrayList<Asignatura> listaAsignaturas = (ArrayList<Asignatura>) serviciosDao.findAllAsignatura();
		Asignatura asignatura = new Asignatura();
		asignatura.setAsignatura("otra");
		asignatura.setId_asignatura((long) -1);
		listaAsignaturas.add(asignatura);

		model.addAttribute("item", item);
		model.addAttribute("biblioteca", bilioteca);
		model.addAttribute("asignaturas", listaAsignaturas);

		model.addAttribute("titulo", "Editar Acervo");
		return "/Acervo/formAcervo";
	}
	
	public Long obtenerId_escuela_del_usuario(Authentication authentication) {
		Usuario usuario=serviciosDao.findUsuarioByCorreo(authentication.getName().toString());
		if (usuario!=null) {
			Escuela escuela=usuario.getEscuela();
			return escuela.getCooperativa().getClave_cooperatival();
			
			 
		}
		
		
		return (long)-1;
	}
	

}
