package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;


public class Usuariojson implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id_usuario;

	
	private String correo;
	private String contrasena;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	public Long getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido_paterno() {
		return apellido_paterno;
	}
	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}
	public String getApellido_materno() {
		return apellido_materno;
	}
	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}
	public Usuariojson() {
	
	}
	

}
