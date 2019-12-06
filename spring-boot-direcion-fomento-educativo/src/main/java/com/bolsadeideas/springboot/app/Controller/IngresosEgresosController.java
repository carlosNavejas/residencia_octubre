package com.bolsadeideas.springboot.app.Controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;

import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;

import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@RequestMapping(value = "/ingresos")
@SessionAttributes("ingresos")
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

		Date fecha = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		int inicio = calendar.get(Calendar.YEAR);
		int fin = inicio + 1;

		Cooperativa cooperativa = servicioDaos.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "ha ocurrido un error... Intentelo mas tarde");
			return "redirect:/home";
		}
		int numeroSocios = servicioDaos.contarSociosPorCooperativa(id);

		Ingresos_egresos ingresos = new Ingresos_egresos();
		ingresos.setNumeroSocios(numeroSocios);
		ingresos.setCooperativa(cooperativa);
		ingresos.setCiclo(inicio + "-" + fin);
		model.addAttribute("ingresos", ingresos);
		model.addAttribute("tipos", tipoR);
		model.addAttribute("id", id);
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

	@RequestMapping(value = "{id}/historial-ingresosegresos/", method = RequestMethod.GET)

	public String listar(@PathVariable(value = "id") Long id, @RequestParam(name = "page", defaultValue = "0") int page,
			Model model, RedirectAttributes flash) {
		Cooperativa cooperativa;

		if (id <= 0) {
			flash.addFlashAttribute("error", "Ha ocurrido un error,Intentelo mas tarde");
			return "redirect:/home";
		}
		cooperativa = servicioDaos.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "Aun no has registrado una cooperativa");
			return "redirect:/home";
		}

		Pageable pageRequest = PageRequest.of(page, 10);
		Page<Ingresos_egresos> lescuelas = servicioDaos.findAllIngresosEgresos(id, pageRequest);
		PageRender<Ingresos_egresos> pageRender = new PageRender<Ingresos_egresos>(
				id + "/ingresos/historial-ingresosegresos/", lescuelas);

		model.addAttribute("titulo", "Historial de ingresos ");
		model.addAttribute("ingresoss", lescuelas);
		model.addAttribute("page", pageRender);
		model.addAttribute("id", id);

		return "/ingresosEgresos/lista_ingresosEgresosAnteriores";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Ingresos_egresos ingresos, BindingResult result, Model model,
			@RequestParam(name = "id_cooperativa", required = true) Long id_cooperativa,
			@RequestParam(name = "date", required = false) String date, RedirectAttributes flash,
			SessionStatus status) {

		String mensajeFlash = (ingresos.getId_ingreosegreos() != null) ? "Editado con éxito!!!"
				: "Registro con éxito!!!";

		Cooperativa cooperativa = servicioDaos.findOneCooperativaById(id_cooperativa);
		ingresos.setCooperativa(cooperativa);
		if (date != null) {
			SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");

			Date fechaAntesMod = new Date();
			try {
				fechaAntesMod = dateformat2.parse(date);
				ingresos.setFecharegistro(fechaAntesMod);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		servicioDaos.saveIngresosEgresos(ingresos);

		status.setComplete();
		flash.addFlashAttribute("info", mensajeFlash);
		return "redirect:/home";
	}

	// obtener numero de socios activos
	@GetMapping(value = "/cargarnumSocios/{term}", produces = "application/json")
	public @ResponseBody int cargarEscuela(@PathVariable Long term) {
		int numeroDeSocios = servicioDaos.contarSociosPorCooperativa(term);
		return numeroDeSocios;

	}

	// historial por ciclo escolar
	@RequestMapping(value = "{id}/historial-ingresosegresosCiclo/", method = RequestMethod.GET)
	public String listar_cicloEscolar(@PathVariable(value = "id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page, Model model, RedirectAttributes flash) {
		Cooperativa cooperativa;
		if (id <= 0) {
			flash.addFlashAttribute("error", "Ha ocurrido un error,Intentelo mas tarde");
			return "redirect:/home";
		}
		cooperativa = servicioDaos.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("error", "Aun no has registrado una cooperativa");
			return "redirect:/home";
		}
		Pageable pageRequest = PageRequest.of(page, 10);
		Date fechaActual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaActual);
		int inicio = calendar.get(Calendar.YEAR);
		int fin = inicio + 1;
		SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");
		String strdate2 = inicio + "-08-01";
		String strdate1 = fin + "-06-30";
		Date newdate1 = new Date();
		Date newdate2 = new Date();
		try {
			newdate1 = dateformat2.parse(strdate1);
			newdate2 = dateformat2.parse(strdate2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		Page<Ingresos_egresos> lescuelas = servicioDaos.buscarIngresosEntreFechas(id, newdate2, newdate1, pageRequest);
		PageRender<Ingresos_egresos> pageRender = new PageRender<Ingresos_egresos>(
				id + "/ingresos/historial-ingresosegresos/", lescuelas);
		model.addAttribute("titulo", "Historial de ingresos ");
		model.addAttribute("ingresoss", lescuelas);
		model.addAttribute("page", pageRender);
		model.addAttribute("id", id);
		return "/ingresosEgresos/lista_ingresosEgresosCiclo";
	}

	// Editar
	@RequestMapping(value = "{id}/Editarregistrar-ingresos")
	public String formIngresosEgresosEditar(@PathVariable(value = "id") Long id, Model model,
			RedirectAttributes flash) {
		if (id < 0) {
			flash.addFlashAttribute("error", "ha ocurrido un error... Intentelo mas tarde");
			return "redirect:/home";
		}
		Date fecha = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);

		Ingresos_egresos ingresos = servicioDaos.findIngresosEgresosByID(id);
		if (ingresos == null) {
			flash.addFlashAttribute("error", "ha ocurrido un error... Intentelo mas tarde");
			return "redirect:/home";
		}

		model.addAttribute("ingresos", ingresos);
		model.addAttribute("tipos", tipoR);

		model.addAttribute("titulo", "Registro de ingresos y egresos");
		return "/ingresosEgresos/formularioEditar";
	}
}
