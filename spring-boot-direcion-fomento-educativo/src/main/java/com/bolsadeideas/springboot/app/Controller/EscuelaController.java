package com.bolsadeideas.springboot.app.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.models.entity.Escuela;

@Controller
@RequestMapping(value = "/escuelas")

public class EscuelaController {
	
	@RequestMapping(value = "/form")
	public String formulario_escuela(Model model) {
		Escuela escuela=new Escuela();
		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Registro de escuela");
		return "escuelas/formEscuelas";
	}
	

}
