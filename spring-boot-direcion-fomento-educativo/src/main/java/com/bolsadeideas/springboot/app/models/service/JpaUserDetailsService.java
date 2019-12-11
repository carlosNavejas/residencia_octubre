package com.bolsadeideas.springboot.app.models.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.app.models.entity.Rol;
import com.bolsadeideas.springboot.app.models.entity.Usuario;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {
	@Autowired
	private IUsuarioDao usuarioDao;
	private Logger logger = LoggerFactory.getLogger(JpaUserDetailsService.class);
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByCorreo(username);
		if (usuario == null) {
			logger.info("No existe el usuario" + username);
			throw new UsernameNotFoundException("El usuario no existe en el sistema");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Rol rolin : usuario.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(rolin.getNombrerol()));
			logger.info("Role " + rolin.getNombrerol());
		}
		if (authorities.isEmpty()) {
			logger.info("No tienes los privilegios necesarios");
			throw new UsernameNotFoundException("No puedes iniciar sesion \nNo tienes los privilegios necesarios");
		}
		return new User(usuario.getCorreo(), usuario.getContrasena(), usuario.getEstado(), true, true, true,
				authorities);
	}

}
