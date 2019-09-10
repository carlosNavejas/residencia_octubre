package com.bolsadeideas.springboot.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/escuela")
@SessionAttributes("escuela")
public class Escuela_controller {

	public String guardarEscuela() {

		return "redirect:escuelas";
	}

	public String formularioEscuela() {

		return "formEscuela";
	}

	public String eliminarEscuela() {

		return "eliminarEscuela";
	}

	public String verDetallesEscuela() {

		return "escuela";
	}

	public String verEscuelas() {

		return "escuelas";
	}
}
