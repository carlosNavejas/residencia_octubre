package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.bolsadeideas.springboot.app.auth.handler.LoginSuccesHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoginSuccesHandler succesHandler;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JpaUserDetailsService detalleServicespa;

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

		builder.userDetailsService(detalleServicespa)
		.passwordEncoder(passwordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/usuarios/guardar","/ingresos/cargarnumSocios/**","/usuarios/buscarCorreo/**","/usuarios/cargarEscuela/**","/","/usuarios/form", "/direcciondefomento", "/inicio", "/home", "/css/**", "/js/**", "/error/**",
						"/imagenesLogos/**")
				.permitAll().anyRequest().authenticated().and().formLogin().successHandler(succesHandler)
				.loginPage("/login").permitAll().and().logout().permitAll().and().exceptionHandling()
				.accessDeniedPage("/error_403");

	}

}
