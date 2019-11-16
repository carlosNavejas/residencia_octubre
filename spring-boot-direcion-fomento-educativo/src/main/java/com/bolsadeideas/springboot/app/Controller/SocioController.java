package com.bolsadeideas.springboot.app.Controller;

import java.util.Arrays;
import java.util.List;

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

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/socios")
@SessionAttributes("socio")
public class SocioController {
	@Autowired
	private ServiciosServiceImpl servicios;
	List<String> guposAZ = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
	List<String> estadosE = Arrays.asList("ACTIVO", "INACTIVO");

	@RequestMapping(value = "/form/{id}")
	public String crear(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Socio socio = new Socio();
		Escuela escuela = servicios.findbyIdEscuela(id);
		if (escuela == null || id <= 0 || escuela.getCooperativa() == null) {
			flash.addFlashAttribute("error", "Ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		socio.setCooperativa(escuela.getCooperativa());
		model.addAttribute("socio", socio);
		model.addAttribute("grupos", guposAZ);
		model.addAttribute("estadoE", estadosE);
		model.addAttribute("titulo", "Registrar socios");
		return "Socios/formulario_socios_cooperativa";
	}

	// Listar socios de una cooperativa especifica
	@RequestMapping(value = "/listar/{id}/", method = RequestMethod.GET)
	public String listar(@PathVariable(value = "id") Long id, @RequestParam(name = "page", defaultValue = "0") int page,
			Model model, RedirectAttributes flash) {
		Cooperativa cooperativa;
		if (id <= 0) {
			flash.addFlashAttribute("error", "Ha ocurrido un error,Intentelo mas tarde");
			return "redirect:/home";
		}
		cooperativa = servicios.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "Aun no has registrado una cooperativa");
			return "redirect:/home";
		}

		Pageable pageRequest = PageRequest.of(page, 10);

		Page<Socio> lescuelas = servicios.findSocioByCooperativa(id, pageRequest);
		PageRender<Socio> pageRender = new PageRender<Socio>("/escuelas/listar", lescuelas);
		model.addAttribute("titulo", "Listado de socios");
		model.addAttribute("socioss", lescuelas);
		model.addAttribute("page", pageRender);
		model.addAttribute("id", id);
		return "/Socios/lista_socios_cooperativa";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Socio socio, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = (socio.getId_socio() != null) ? "Editado con éxito!!!" : "Registro con éxito!!!";
		servicios.saveSocioDeCooperativa(socio);
		status.setComplete();
		flash.addFlashAttribute("info", mensajeFlash);
		return "redirect:/socios/listar/" + socio.getCooperativa().getClave_cooperatival() + "/";
	}

	/* Eliminar Socio */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Socio socio = servicios.findSocioById(id);
			if (socio != null) {
				servicios.deleteSocioById(id);
				flash.addFlashAttribute("succes",
						"Se ha elimida el registro!!!" + socio.getNombre() + " " + socio.getApellido_p());
				return "redirect:/socios/listar";
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar la escuela!");
		return "redirect:/home";
	}

	@RequestMapping(value = "/editar/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Socio socio = servicios.findSocioById(id);
		if (socio == null) {
			flash.addFlashAttribute("error", "Ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		model.addAttribute("socio", socio);
		model.addAttribute("grupos", guposAZ);
		model.addAttribute("estadoE", estadosE);
		model.addAttribute("titulo", "Editar socios");
		return "Socios/formulario_socios_cooperativa";
	}
//para generar pdf

	@RequestMapping(value = "/listarprint/{id}", method = RequestMethod.GET)
	public String listarpdf(@PathVariable(value = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model, RedirectAttributes flash) {
		Cooperativa cooperativa;
		if (id <= 0) {
			flash.addFlashAttribute("error", "Ha ocurrido un error,Intentelo mas tarde");
			return "redirect:/home";
		}
		cooperativa = servicios.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "Aun no has registrado una cooperativa");
			return "redirect:/home";
		}

		List<Socio> listaSocios = servicios.listarSociosByCooperativa(id);
		model.addAttribute("id", id);
		model.addAttribute("lista", listaSocios);
		return "/Socios/listasocios";
	}
}
