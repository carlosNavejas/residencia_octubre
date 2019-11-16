package com.bolsadeideas.springboot.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/ingresos")
public class IngresosEgresosController {
	public String formIngresosEgresos() {return "";}
	public String formIngresosEgresosEditar() {return "";}
	public String listaIngresosEgresosDelPeriodo() {return "";}
	public String listaIngresosEgresosDelPeriodoHistorial() {return "";}
}
