package com.bolsadeideas.springboot.app.Controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;

import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
@RequestMapping(value = "/bibliotecas")
public class BibliotecaController {

	@Autowired
	private ServiciosServiceImpl servicios;

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {
		Biblioteca biblioteca = new Biblioteca();
		model.put("biblioteca", biblioteca);
		model.put("titulo", "Formulario de bibliotecas");
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
		return "redirect:/bibliotecas/biblioteca/" + id;
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Biblioteca biblioteca, BindingResult result, Model model,
			@RequestParam(name = "id_escuela", required = false) Long id_escuela, RedirectAttributes flash,
			SessionStatus status) {

		Escuela escuela = servicios.findbyIdEscuela(id_escuela);
		biblioteca.setEscuela(escuela);
		servicios.saveBiblioteca(biblioteca);
		flash.addFlashAttribute("success", "Se ha guardado la biblioteca");
		return "redirect:/bibliotecas/biblioteca/" + escuela.getId_escuela();
	}

	@RequestMapping(value = "/biblioteca/{id}")
	public String verDetallesdeLaCooperativa(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		Escuela escuela = null;
		if (id > 0) {
			escuela = servicios.findbyIdEscuela(id);
			if (escuela == null) {
				flash.addFlashAttribute("error", "Ha ocurrido un error!");
				return "/bibliotecas/ver";
				// return "redirect:/cooperativas/cooperativa/" + id;
			}
		}

		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Editar Cooperativa");
		return "/bibliotecas/ver";
	}

}
