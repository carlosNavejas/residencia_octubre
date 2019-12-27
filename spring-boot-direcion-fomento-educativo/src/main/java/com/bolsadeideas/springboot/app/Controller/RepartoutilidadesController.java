package com.bolsadeideas.springboot.app.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;

import com.bolsadeideas.springboot.app.models.entity.Reparto_utilidades;

import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@SessionAttributes("reparto")
@Controller
@RequestMapping("/reparto-utilidades")
public class RepartoutilidadesController {
	@Autowired
	private ServiciosServiceImpl serviciosDaos;

	@RequestMapping(value = "{idbuscar}/listar/", method = RequestMethod.GET)
	public String listar(@PathVariable(name = "idbuscar") Long idbuscar,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model, RedirectAttributes flash) {
		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Reparto_utilidades> lescuelas = serviciosDaos.findRepartoUtilidadesByIdCoopeartiva(idbuscar, pageRequest);
		PageRender<Reparto_utilidades> pageRender = new PageRender<Reparto_utilidades>("/usuarios/listar", lescuelas);
		model.addAttribute("idCooperativa", idbuscar);
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuaurios", lescuelas);
		model.addAttribute("page", pageRender);
		return "/RepartoUtilidades/listar_repartos";
	}

	@RequestMapping(value = "{id}/registro-reparto")
	public String formulario_escuela(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			Cooperativa cooperativa = serviciosDaos.findOneCooperativaById(id);
			if (cooperativa == null) {
				return "redirect:/home";
			}
			
			
			
			Reparto_utilidades reparto = new Reparto_utilidades();
			reparto.setCooperativa(cooperativa);
			model.addAttribute("reparto", reparto);
			model.addAttribute("titulo", "Registro Reparto utilidades");
			return "/RepartoUtilidades/repartoForm";
		}
		return "redirec:/home";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@RequestParam(name="fecha",required = false)String fecha, @RequestParam(name = "id_coo", required = true) String id_coo, Reparto_utilidades reparto,
			Model model, RedirectAttributes flash, SessionStatus status) {

		if (fecha != null) {
			SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");

			Date fechaAntesMod = new Date();
			try {
				fechaAntesMod = dateformat2.parse(fecha);
				reparto.setFechaRegistro(fechaAntesMod);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		String mensaje = (reparto.getId_reparto() != null) ? "Editado con exito"
				: "Se ha registrado el reparto de utilidades";
		Cooperativa cooperativa = serviciosDaos.findOneCooperativaById(Long.parseLong(id_coo));
		reparto.setCooperativa(cooperativa);
		serviciosDaos.saveRepartoUtilidades(reparto);
		flash.addFlashAttribute("info", mensaje);
		status.setComplete();
		return "redirect:/reparto-utilidades/" + id_coo + "/listar/";
	}

//Editar
	@RequestMapping(value = "{id}/editarregistro-reparto")
	public String formulario_escuelaEditar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			Reparto_utilidades reparto = serviciosDaos.findByClaveRepartoUtilidades(id);

			if (reparto == null) {
				flash.addFlashAttribute("info", "Nom se encontro reparto");
				return "redirect:/home";
			}
			model.addAttribute("reparto", reparto);
			model.addAttribute("titulo", "Editar registro Reparto utilidades");
			return "/RepartoUtilidades/repartoForm";
		}
		return "redirec:/home";
	}
}
