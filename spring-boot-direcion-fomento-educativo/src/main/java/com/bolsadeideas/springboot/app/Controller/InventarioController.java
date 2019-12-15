package com.bolsadeideas.springboot.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar_item_inventario(Item_inventario item,
			@RequestParam(name = "otro", required = false) String otro,
			@RequestParam(name = "id_item_inventario", required = false) Long id_item_inventario,
			@RequestParam(name = "cooperativa_id", required = true) Long cooperativa_id, Model model,
			RedirectAttributes flash, SessionStatus status) {

		return "redirect:/home";
	}

}
