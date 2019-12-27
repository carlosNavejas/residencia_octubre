package com.bolsadeideas.springboot.app.Controller;

import java.lang.module.FindException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.bolsadeideas.springboot.app.models.entity.Item_inventario;
import com.bolsadeideas.springboot.app.models.entity.Mueble;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@RequestMapping("/inventario")
@SessionAttributes("item")
public class InventarioController {
	@Autowired
	private ServiciosServiceImpl serviciosDao;

	@RequestMapping("{id}/registrar-inventario")
	public String formInventario(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id < 1) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}
		Cooperativa cooperativa = serviciosDao.findOneCooperativaById(id);
		if (cooperativa == null) {
			flash.addFlashAttribute("info", "Debe registrar una cooperativa");
			return "redirect:/home";
		}
		List<Mueble> listaM = serviciosDao.findAllMuebles();
		Mueble mueble = new Mueble();
		mueble.setId((long) -1);
		mueble.setMueble("otro");
		listaM.add(mueble);
		Item_inventario item = new Item_inventario();
		model.addAttribute("muebles", listaM);
		model.addAttribute("item", item);
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Registro de inventario");
		return "/Inventario/inventarioFormulario";
	}

	@RequestMapping("{id}/editar-inventario")
	public String formEditarInventario(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id < 1) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}

		Item_inventario item = serviciosDao.findItem_inventarioById(id);
		if (item == null) {
			flash.addFlashAttribute("error", "ha ocurrido un error, intentelo mas tarde");
			return "redirect:/home";
		}

		Cooperativa cooperativa = item.getCooperativa();
		model.addAttribute("item", item);
		model.addAttribute("cooperativa", cooperativa);
		model.addAttribute("titulo", "Registro de inventario");
		return "/Inventario/inventarioFormulario";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar_item_inventario(Item_inventario item,
			@RequestParam(name = "otro", required = false) String otro,
			@RequestParam(name = "id_item_inventario", required = false) Long id_item_inventario,
			@RequestParam(name = "cooperativa_id", required = true) Long cooperativa_id,
			@RequestParam(name = "fecha", required = false) String fecha, Model model, RedirectAttributes flash,
			SessionStatus status) {
		String mensaje = (item.getId_item_inventario() != null) ? "Se ha editado correctamente"
				: "Se ha registrado correctamente";
		if (cooperativa_id > 0) {
			Cooperativa cooperativa = serviciosDao.findOneCooperativaById(cooperativa_id);
			if (cooperativa != null) {
				item.setCooperativa(cooperativa);
				if (item.getId_item_inventario() != null) {
					Mueble mueble = new Mueble();
					mueble.setMueble(item.getMueble().getMueble());
					mueble.setItem(item);
					mueble.setId(item.getMueble().getId());
					item.setMueble(mueble);
					SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date fechaAntesMod = new Date();
					try {
						fechaAntesMod = dateformat2.parse(fecha);
						item.setFecha_registro(fechaAntesMod);
					} catch (Exception e) {
						// TODO: handle exception
					}
				} else if (item.getId_item_inventario() == null) {
					Mueble mueble = new Mueble();
					mueble.setMueble(item.getMueble().getMueble());
					mueble.setItem(item);
					item.setMueble(mueble);

				}

				serviciosDao.saveItemIventario(item);
				status.setComplete();
				flash.addFlashAttribute("info", mensaje);
				return "redirect:/home";
			}
		}

		flash.addFlashAttribute("error", "Ha ocurrido un error al registrar en el inventario, intentelo mas tarde");
		return "redirect:/home";
	}

	@RequestMapping(value = "{id}/listar", method = RequestMethod.GET)
	public String listar(@PathVariable(name = "id") Long id, @RequestParam(name = "page", defaultValue = "0") int page,
			Model model, HttpServletRequest request) {
		Pageable pageRequest = PageRequest.of(page, 10);

		Page<Item_inventario> inventario = serviciosDao.findItemInventarioByCooperativa(id, pageRequest);
		PageRender<Item_inventario> pageRender = new PageRender<Item_inventario>("/escuelas/listar", inventario);
		model.addAttribute("titulo", "Listado de escuelas");
		model.addAttribute("escuelas", inventario);
		model.addAttribute("page", pageRender);
		return "/inventario/inventarioListar";
	}

	/* Eliminar escuela */
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			Item_inventario item = serviciosDao.findItem_inventarioById(id);
			if (item == null) {
				flash.addFlashAttribute("error", "Ha ocurrido un error intentelo mas tarde");
				return "redirect:/home";
			}
			serviciosDao.deleteIntemInventario(id);
			flash.addFlashAttribute("success", "Se ha eliminado con éxito!");
			return "redirect:/inventario/" + item.getCooperativa().getClave_cooperatival() + "/listar";
		}

		flash.addFlashAttribute("error", "Ha ocurrido un error al eliminar la escuela!");
		return "redirect:/escuelas/listar";
	}

}
