package com.bolsadeideas.springboot.app.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

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

import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
@RequestMapping(value = "/cooperativas")
@SessionAttributes("cooperativa")
public class CooperativasController {
	@Autowired
	private ServiciosServiceImpl servicios;

	/* Editar cooperativa */
	@RequestMapping(value = "/editarCooperativa/{id}")
	public String editarCooperativa(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Escuela escuela = null;
		Cooperativa cooperativa = new Cooperativa();
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela == null) {
				flash.addFlashAttribute("error", "Ha ocurrido un error!");
				return "redirect:/cooperativas/cooperativa/" + id;
			}
		}
		cooperativa = escuela.getCooperativa();
		model.addAttribute("escuela", escuela);
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Editar Cooperativa");
		return "cooperativas/formularioEditarCooperativa";
	}

	/*
	 * Formulario para registrar una cooperativa nueva en este caso va a recibir el
	 * id de la escuela a donde se registrara
	 */
	@RequestMapping(value = "/registrarCooperativa/{id}")
	public String formularioRegistroNuevo(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Escuela escuela = null;
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela != null) {
				Cooperativa cooperativa = new Cooperativa();
				model.addAttribute("escuela", escuela);
				model.addAttribute("cooperativa", cooperativa);
				model.addAttribute("titulo", "Registar Cooperativa");
				return "cooperativas/formularioCooperativa";
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al registar la escuela");
		model.addAttribute("titulo", "Detalle de la cooperativa");
		return "cooperativas/formularioCooperativa";
	}

	/* Ver detalles de la cooperativa */
	/* Editar cooperativa */
	@RequestMapping(value = "/cooperativa/{id}")
	public String verDetallesdeLaCooperativa(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Escuela escuela = null;
		Cooperativa cooperativa = new Cooperativa();
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela == null) {
				flash.addFlashAttribute("error", "Ha ocurrido un error!");
				return "redirect:/cooperativas/cooperativa/" + id;
			}
		}
		cooperativa = escuela.getCooperativa();
		model.addAttribute("escuela", escuela);
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Editar Cooperativa");
		return "/cooperativas/detalleCoopeativa";
	}

	/* Eliminar escuela */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Cooperativa cooperativa = servicios.findOneCooperativaById(id);
			if (cooperativa != null) {
				
				servicios.deleteCooperativaById(cooperativa.getClave_cooperatival());
				flash.addFlashAttribute("success", "Cooperativa eliminada con éxito!");
				return "redirect:/cooperativas/cooperativa/" +cooperativa.getEscuela().getId_escuela();
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar la cooperativa!");
		return "redirect:/cooperativas/cooperativa/" + id;
	}
	/* Guardar cooperativa */
    
	
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Cooperativa cooperativa, BindingResult result, Model model,
			@RequestParam(name = "id_escuela", required = false) Long id_escuela, RedirectAttributes flash,
			SessionStatus status) {
		Escuela escuela = servicios.findbyIdEscuela(id_escuela);
		//escuela.setCooperativa(cooperativa);
		
		cooperativa.setEscuela(escuela);
		servicios.saveCooperativa(cooperativa);
		flash.addFlashAttribute("success", "Se ha guardado la cooperativa");
		return "redirect:/cooperativas/cooperativa/" + escuela.getId_escuela();
	}

}
