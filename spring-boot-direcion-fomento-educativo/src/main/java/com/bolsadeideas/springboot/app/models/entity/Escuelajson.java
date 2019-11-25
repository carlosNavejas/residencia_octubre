package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

public class Escuelajson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id_escuela;
	private String claveescuela;
	private String nombre_escuela;
	private String telefono;
	private String turno;
	private String tipo;

	private String direCcion;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getId_escuela() {
		return id_escuela;
	}

	public void setId_escuela(Long id_escuela) {
		this.id_escuela = id_escuela;
	}

	public String getClaveescuela() {
		return claveescuela;
	}

	public void setClaveescuela(String claveescuela) {
		this.claveescuela = claveescuela;
	}

	public String getNombre_escuela() {
		return nombre_escuela;
	}

	public void setNombre_escuela(String nombre_escuela) {
		this.nombre_escuela = nombre_escuela;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getDireCcion() {
		return direCcion;
	}

	public void setDireCcion(String direCcion) {
		this.direCcion = direCcion;
	}

	public Escuelajson() {

	}

}
