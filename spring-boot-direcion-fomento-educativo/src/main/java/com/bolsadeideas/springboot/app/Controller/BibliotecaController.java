package com.bolsadeideas.springboot.app.Controller;

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

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;

import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
@SessionAttributes("biblioteca")
@RequestMapping(value = "/bibliotecas")
public class BibliotecaController {

	@Autowired
	private ServiciosServiceImpl servicios;

	@RequestMapping(value = "/form/{id}")
	public String crear(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Biblioteca biblioteca = new Biblioteca();
		Escuela escuela = servicios.findbyIdEscuela(id);
		if (escuela == null || id <= 0) {
			flash.addFlashAttribute("error", "Ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		biblioteca.setEscuela(escuela);
		model.addAttribute("biblioteca", biblioteca);
		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Formulario de bibliotecas");
		return "Bibliotecas/registarBiblioteca";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String delete(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			Biblioteca biblioteca = servicios.findByIdBiblioteca(id);
			if (biblioteca != null) {
				servicios.deleteBibliotecaByID(id);
				flash.addFlashAttribute("success", "Biblioteca eliminada con éxito!");
				return "redirect:/bibliotecas/biblioteca/" + biblioteca.getEscuela().getId_escuela();
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar la cooperativa!");
		return "redirect:/home";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Biblioteca biblioteca, BindingResult result, Model model,
			@RequestParam(name = "id_escuela", required = false) Long id_escuela, RedirectAttributes flash,
			SessionStatus status) {
		if (biblioteca.getClave_biblioteca() != null) {
			servicios.saveBiblioteca(biblioteca);
			status.setComplete();
			flash.addFlashAttribute("success", "Se ha editado la biblioteca");

			return "redirect:/bibliotecas/biblioteca/" + id_escuela;
		}
		Escuela escuela = servicios.findbyIdEscuela(id_escuela);
		biblioteca.setEscuela(escuela);
		servicios.saveBiblioteca(biblioteca);
		status.setComplete();
		flash.addFlashAttribute("success", "Se ha registrado la biblioteca");
		return "redirect:/bibliotecas/biblioteca/" + id_escuela;
	}

	@RequestMapping(value = "/biblioteca/{id}")
	public String verDetallesdeLaCooperativa(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Escuela escuela = null;
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);

			if (escuela != null) {
				model.addAttribute("escuela", escuela);
				model.addAttribute("titulo", "Detalles de la biblioteca");
				return "/bibliotecas/ver";
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error!!");
		return "redirect:/home";
	}

	@RequestMapping(value = "/registarBiblioteca/{id}")
	public String formularioRegistroNuevo(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Escuela escuela = null;
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela != null) {
				Biblioteca biblioteca = escuela.getBiblioteca();
				model.addAttribute("escuela", escuela);
				model.addAttribute("biblioteca", biblioteca);
				model.addAttribute("titulo", "Editar Biblioteca");
				return "Bibliotecas/editarBiblioteca";
			}
		}
		flash.addFlashAttribute("error", "Ha ocurrido un error al editar la cooperativa");
		model.addAttribute("titulo", "Inicio");
		return "redirect:/home";
	}
}
