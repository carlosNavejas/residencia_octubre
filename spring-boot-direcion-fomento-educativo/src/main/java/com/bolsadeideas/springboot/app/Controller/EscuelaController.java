package com.bolsadeideas.springboot.app.Controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Direccion;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Municipio;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/escuelas")
@SessionAttributes("escuela")
public class EscuelaController {
	@Autowired
	private ServiciosServiceImpl servicios;

	/* Formulario para registrar la escuela */
	
	@RequestMapping(value = "/form")
	public String formulario_escuela(Model model) {
		Escuela escuela = new Escuela();
		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Registro de escuela");
		return "escuelas/formEscuelas";
	}

	
	/* Guarda la escuela */
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Escuela escuela, @RequestParam(name = "calle", required = false) String calle,
			@RequestParam(name = "municipio", required = false) Long municipio,
			@RequestParam(name = "numeroc", required = false) Integer numeroc, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (escuela.getId_escuela() != null) {
			servicios.guardarEscuela(escuela);
			status.isComplete();
			flash.addFlashAttribute("info", "Se ha modificado la escuela");
			return "redirect:/escuelas/listar";
		}

		if (servicios.findByClaveEscuela(escuela.getClaveescuela()) != null) {
			flash.addFlashAttribute("warning",
					"La clave de la escuela ya ha sido registrada" + escuela.getClaveescuela());
			return "redirect:/escuelas/form";
		}
		Municipio municipioObtenido = servicios.findMunicipioByID(municipio);
		Direccion direccion = new Direccion();
		direccion.setCalle(calle);
		direccion.setNumero(numeroc + "");
		direccion.setMunicipio(municipioObtenido);
		escuela.setDireccion(direccion);
		servicios.guardarEscuela(escuela);
		status.isComplete();

		flash.addFlashAttribute("info", "Se ha registrado la escuela");
		return "redirect:/escuelas/listar";
	}

	/* Mostrar las escuelas */
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Escuela> lescuelas = servicios.findAll(pageRequest);
		PageRender<Escuela> pageRender = new PageRender<Escuela>("/escuelas/listar", lescuelas);
		model.addAttribute("titulo", "Listado de escuelas");
		model.addAttribute("escuelas", lescuelas);
		model.addAttribute("page", pageRender);
		return "/escuelas/lista_escuelas";
	}

	/* Eliminar escuela */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			servicios.deleteEscuela(id);
			flash.addFlashAttribute("success", "Escuela eliminada con éxito!");
			return "redirect:/escuelas/listar";
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar la escuela!");
		return "redirect:/escuelas/listar";
	}

	/* Formulario para Editar la escuela */
	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Escuela escuela = null;
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/escuelas/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El no se encontro la clave!");
			return "redirect:/listar";
		}
		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Editar Cliente");
		return "escuelas/formEscuelasEditar";
	}
}
