package com.bolsadeideas.springboot.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;

@Controller
public class HomeController {
	@Autowired
	ServiciosServiceImpl serviciosDao;

	@RequestMapping(value = { "/", "/direcciondefomento", "/inicio", "/home" })
	public String iniciando(Authentication authentication) {
		//model.addAttribute("error", obtenerId_escuela_del_usuario(authentication));
		return "inicio_menu";
	}

	public String obtenerId_escuela_del_usuario(Authentication authentication) {
		Usuario usuario = serviciosDao.findUsuarioByCorreo(authentication.getName().toString());
		// if (usuario!=null) {

		// Escuela escuela=usuario.getEscuela();
		// return escuela.getCooperativa().getClave_cooperatival();

		// }

		return usuario.toString();
	}
}
