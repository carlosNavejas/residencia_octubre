package com.bolsadeideas.springboot.app.Controller;

import java.util.Arrays;
import java.util.List;

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

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;

import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;

import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
@RequestMapping(value = "/ingresos")
public class IngresosEgresosController {
	@Autowired
	private ServiciosServiceImpl servicioDaos;
	List<String> tipoR = Arrays.asList("Inicio Curso", "Mensual", "Medio curso", "Fin de Curso");

	@RequestMapping(value = "{id}/registrar-ingresos")
	public String formIngresosEgresos(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id < 0) {
			flash.addFlashAttribute("error", "ha ocurrido un error... Intentelo mas tarde");
			return "redirect:/home";
		}
		Cooperativa cooperativa = servicioDaos.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "ha ocurrido un error... Intentelo mas tarde");
			return "redirect:/home";
		}

		Ingresos_egresos ingresos = new Ingresos_egresos();
		ingresos.setCooperativa(cooperativa);
		model.addAttribute("ingresos", ingresos);
		model.addAttribute("tipos", tipoR);
		model.addAttribute("titulo", "Registro de ingresos y egresos");
		return "/ingresosEgresos/formulario";
	}

	@RequestMapping(value = "/editar-inggresos")
	public String formIngresosEgresosEditar() {
		return "/ingresosEgresos/formulario";
	}

	@RequestMapping(value = "/ingresos-egresosdelperiodo")
	public String listaIngresosEgresosDelPeriodo() {
		return "/ingresosEgresos/detalle_ingresosEgresos";
	}

	@RequestMapping(value = "/historial-ingresosegresos")
	public String listaIngresosEgresosDelPeriodoHistorial() {
		return "/ingresosEgresos/lista_ingresosEgresosAnteriores";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@Valid Ingresos_egresos ingresos, BindingResult result, Model model,
			@RequestParam(name = "id_cooperativa", required = true) Long id_cooperativa, RedirectAttributes flash,
			SessionStatus status) {
		String mensajeFlash = (ingresos.getId_ingreosegreos() != null) ? "Editado con éxito!!!"
				: "Registro con éxito!!!";
		servicioDaos.saveIngresosEgresos(ingresos);
		status.setComplete();
		flash.addFlashAttribute("info", mensajeFlash);

		return "redirect/home";
	}
}
