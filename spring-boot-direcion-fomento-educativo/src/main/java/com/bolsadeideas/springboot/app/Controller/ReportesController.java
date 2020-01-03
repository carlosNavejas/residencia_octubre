package com.bolsadeideas.springboot.app.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;
import com.bolsadeideas.springboot.app.models.entity.Cooperativa;
import com.bolsadeideas.springboot.app.models.entity.Item_acervo;
import com.bolsadeideas.springboot.app.models.entity.Item_inventario;
import com.bolsadeideas.springboot.app.models.entity.Region;
import com.bolsadeideas.springboot.app.models.entity.RegionesEscuelas;
import com.bolsadeideas.springboot.app.models.entity.Socio;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
public class ReportesController {
	@Autowired
	private ServiciosServiceImpl serviciosDao;

	@RequestMapping("/escuelas-por-region")
	public String escuelasPorRegion(Model model, RedirectAttributes flass) {
		List<Region> regiones = serviciosDao.finAllRegiones();
		List<RegionesEscuelas> listaRegionesEscuelas = new ArrayList<RegionesEscuelas>();
		if (regiones.isEmpty()) {
			flass.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		for (Region region : regiones) {
			int numEscuelas = serviciosDao.findEscuelaCountByRegion(region.getId());
			RegionesEscuelas regEscuelas = new RegionesEscuelas();
			regEscuelas.setCantidadEscuelas(numEscuelas);
			regEscuelas.setMunicipio(region.getRegion());
			listaRegionesEscuelas.add(regEscuelas);
		}
		if (listaRegionesEscuelas.isEmpty()) {
			flass.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		model.addAttribute("escuelas", listaRegionesEscuelas);
		model.addAttribute("titulo", "Escuelas por region");
		return "/Graficos/EscuelasRegion";
	}

	// pdfS
	@RequestMapping("/escuelas-por-regiones/")
	public String escuelasPorRegionpdf(Model model) {
		List<Region> regiones = serviciosDao.finAllRegiones();
		List<RegionesEscuelas> listaRegionesEscuelas = new ArrayList<RegionesEscuelas>();
		for (Region region : regiones) {
			int numEscuelas = serviciosDao.findEscuelaCountByRegion(region.getId());
			RegionesEscuelas regEscuelas = new RegionesEscuelas();
			regEscuelas.setCantidadEscuelas(numEscuelas);
			regEscuelas.setMunicipio(region.getRegion());
			listaRegionesEscuelas.add(regEscuelas);
		}
		model.addAttribute("escuelas", listaRegionesEscuelas);
		model.addAttribute("titulo", "Escuelas por region");
		return "/Pdfs/EscuelasRegiones";
	}

	@RequestMapping("/cooperativas-por-regiones/")
	public String cooperativasPorRegionpdf(Model model) {
		List<Region> regiones = serviciosDao.finAllRegiones();
		List<RegionesEscuelas> listaRegionesEscuelas = new ArrayList<RegionesEscuelas>();
		for (Region region : regiones) {
			int numEscuelas = serviciosDao.findCooperativasescuelasByRegion(region.getId());
			RegionesEscuelas regEscuelas = new RegionesEscuelas();
			regEscuelas.setCantidadEscuelas(numEscuelas);
			regEscuelas.setMunicipio(region.getRegion());
			listaRegionesEscuelas.add(regEscuelas);
		}
		model.addAttribute("escuelas", listaRegionesEscuelas);
		model.addAttribute("titulo", "Cooperativas por region");
		return "/Pdfs/CooperativasRegiones";
	}

	@RequestMapping("/bibliotecas-por-regiones/")
	public String bibliotecasPorRegionpdf(Model model) {
		List<Region> regiones = serviciosDao.finAllRegiones();
		List<RegionesEscuelas> listaRegionesEscuelas = new ArrayList<RegionesEscuelas>();
		for (Region region : regiones) {
			int numEscuelas = serviciosDao.findBibliotecasescuelasByRegion(region.getId());
			RegionesEscuelas regEscuelas = new RegionesEscuelas();
			regEscuelas.setCantidadEscuelas(numEscuelas);
			regEscuelas.setMunicipio(region.getRegion());
			listaRegionesEscuelas.add(regEscuelas);
		}

		model.addAttribute("escuelas", listaRegionesEscuelas);
		model.addAttribute("titulo", "Bibliotecas por region");
		return "/Pdfs/BibliotecasRegiones";
	}

	@GetMapping("/{id}/socios-por-cooperativa/")
	public String sociosCooperativapdf(RedirectAttributes flash, Model model, @PathVariable(name = "id") Long id) {

		if (id < 1) {
			flash.addFlashAttribute("error", "Intentelo mas tarde");
			return "redirect:/home";
		}
		Cooperativa cooperativa = serviciosDao.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("info", "Aun no has registrado una cooperativa!!");
			return "redirect:/home";
		}

		ArrayList<Socio> listaSocios = (ArrayList<Socio>) serviciosDao.listarSociosByCooperativa(id);
		if (listaSocios.isEmpty()) {
			flash.addFlashAttribute("info", "Aun no has registrado socios de la cooperativa!!");

			return "redirect:/home";
		}
		model.addAttribute("socios", listaSocios);
		model.addAttribute("titulo", "Listado de socios de cooperativa");
		return "/Pdfs/sociosPorCooperativa";
	}

	@GetMapping("/{id}/acervo-biblioteca/")
	public String acervoBiblioteca(Model model, @PathVariable(name = "id") Long id, RedirectAttributes flas) {

		if (id < 1) {
			flas.addFlashAttribute("error", "Intentelo mas tarde");
			return "redirect:/home";
		}
		Biblioteca biblioteca = serviciosDao.findByIdBiblioteca(id);
		if (biblioteca == null) {
			flas.addFlashAttribute("error", "Aun no has registrado una biblioteca");
			return "redirect:/home";
		}

		List<Item_acervo> listaAcervo = serviciosDao.findItemAcervoByClaveBiblioteca(id);
		if (listaAcervo.isEmpty()) {
			flas.addFlashAttribute("info", "Aun no has registrado asignatura!!!");
			return "redirect:/home";
		}

		model.addAttribute("acervo", listaAcervo);
		model.addAttribute("titulo", "Lista de acervo bibliografico");

		return "/Pdfs/AcervoBiblioteca";
	}

	@GetMapping("/{id}/Inventario-cooperativa/")
	public String inventarioCooperativa(@PathVariable(name = "id") Long id, Model model, RedirectAttributes flas) {
		if (id < 1) {
			flas.addFlashAttribute("error", "Itentelo mas tarde!!!");
			return "redirect:/home";
		}
		Cooperativa cooperativa = serviciosDao.findOneCooperativaById(id);
		if (cooperativa == null) {
			flas.addFlashAttribute("error", "Itentelo mas tarde!!!");
			return "redirect:/home";
		}
		List<Item_inventario> lista_inventario = serviciosDao.findInventarioByCooperativaID(id);

		if (lista_inventario.isEmpty()) {
			flas.addFlashAttribute("info", "Aun no has registrado nada en el inventario!!!");
			return "redirect:/home";
		}
		model.addAttribute("iventario", lista_inventario);
		return "/Pdfs/InventarioCooperativa";
	}
}
