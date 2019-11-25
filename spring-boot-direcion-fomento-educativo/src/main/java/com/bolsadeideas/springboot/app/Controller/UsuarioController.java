package com.bolsadeideas.springboot.app.Controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.bolsadeideas.springboot.app.models.entity.Escuela;
import com.bolsadeideas.springboot.app.models.entity.Escuelajson;

import com.bolsadeideas.springboot.app.models.entity.Usuario;
import com.bolsadeideas.springboot.app.models.entity.Usuariojson;
import com.bolsadeideas.springboot.app.models.service.ServiciosServiceImpl;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("usuario")
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private ServiciosServiceImpl servicios;

	@RequestMapping(value = "/form")
	public String formulario_escuela(Model model) {
		Usuario usuario = new Usuario();
		Escuela escuela = new Escuela();
		usuario.setEscuela(escuela);
		model.addAttribute("usuario", usuario);
		model.addAttribute("escuela", escuela);
		model.addAttribute("titulo", "Registro de usuarios");
		return "Usuarios/form";
	}

	@GetMapping(value = "/cargarEscuela/{term}", produces = "application/json")
	public @ResponseBody Escuelajson cargarEscuela(@PathVariable String term) {
		// return servicios.findByClaveEscuela(term);

		Escuela escuela = servicios.findByClaveEscuela(term);
		Escuelajson esc = new Escuelajson();
		if (escuela != null) {
			esc.setClaveescuela(esc.getClaveescuela());
			esc.setDireCcion(escuela.getDireccion().toString());
			esc.setId_escuela(escuela.getId_escuela());
			esc.setNombre_escuela(escuela.getNombre_escuela());
			esc.setTelefono(escuela.getTelefono());
			esc.setTipo(escuela.getTipo());
			esc.setClaveescuela(escuela.getClaveescuela());
			esc.setTurno(escuela.getTurno());

		}

		return esc;

	}

	@GetMapping(value = "/buscarCorreo/{term}", produces = "application/json")
	public @ResponseBody Usuariojson usuario(@PathVariable String term) {
		Usuario usuario = servicios.findUsuarioByCorreo(term);
		Usuariojson usuariojson = new Usuariojson();
		if (usuario != null) {
			usuariojson.setApellido_materno(usuario.getApellido_materno());
			usuariojson.setApellido_paterno(usuario.getApellido_paterno());
			usuariojson.setContrasena(usuario.getContrasena());
			usuariojson.setCorreo(usuario.getCorreo());
			usuariojson.setId_usuario(usuario.getId_usuario());
			usuariojson.setNombre(usuario.getNombre());
		}

		return usuariojson;

	}

	@RequestMapping(value = "/listar/", method = RequestMethod.GET)
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			RedirectAttributes flash) {
		Pageable pageRequest = PageRequest.of(page, 10);

		Page<Usuario> lescuelas = servicios.findAllUsuarios(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<Usuario>("/escuelas/listar", lescuelas);
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuaurios", lescuelas);
		model.addAttribute("page", pageRender);
		;
		return "/Usuarios/listadeusuarios";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(@RequestParam(name = "claveescuela", required = false) String claveescuela, Usuario usuario,
			BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {

		String mensajeFlash = (usuario.getId_usuario() != null)
				? "Se modificaron los datos del usuario correctamente!!!"
				: "Registro con éxito!!!";
		if (claveescuela == null) {
			servicios.saveUsuario(usuario);
			flash.addFlashAttribute("info", mensajeFlash);
			status.setComplete();
			return "redirect:/usuarios/listar";
		}

		Escuela escuela = servicios.findByClaveEscuela(claveescuela);
		if (escuela != null) {
			flash.addFlashAttribute("error", "Ha ocurrido un error al registrar\nIntetelo mas tarde");
			return "redirect:/home";
		}
		usuario.setEscuela(escuela);
		servicios.saveUsuario(usuario);
		status.setComplete();
		return "redirect:/home";
	}

	public boolean hasRole(String rol) {
		SecurityContext context = SecurityContextHolder.getContext();
		if (context == null) {
			return false;
		}
		Authentication auth = context.getAuthentication();
		if (auth == null) {
			return false;
		}
		Collection<? extends GrantedAuthority> listaAutorizaciones = auth.getAuthorities();
		if (listaAutorizaciones.isEmpty()) {
			return false;
		}
		for (GrantedAuthority rolin : listaAutorizaciones) {
			if (rol.equals(rolin.getAuthority())) {
				return true;
			}
		}
		return false;
	}
}
