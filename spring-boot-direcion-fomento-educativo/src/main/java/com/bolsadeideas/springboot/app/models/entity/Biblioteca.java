package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bibliotecas")
public class Biblioteca implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clave_biblioteca;
	@NotEmpty
	@Column(length = 45)
	private String nombre_bibioteca;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	@OneToOne(fetch = FetchType.LAZY)
	private Escuela escuela;
	public Long getClave_biblioteca() {
		return clave_biblioteca;
	}
	public void setClave_biblioteca(Long clave_biblioteca) {
		this.clave_biblioteca = clave_biblioteca;
	}
	public String getNombre_bibioteca() {
		return nombre_bibioteca;
	}
	public void setNombre_bibioteca(String nombre_bibioteca) {
		this.nombre_bibioteca = nombre_bibioteca;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public Escuela getEscuela() {
		return escuela;
	}
	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}
	public Biblioteca(Long clave_biblioteca, String nombre_bibioteca, Date fecha_registro, Escuela escuela) {
		
		this.clave_biblioteca = clave_biblioteca;
		this.nombre_bibioteca = nombre_bibioteca;
		this.fecha_registro = fecha_registro;
		this.escuela = escuela;
	}
	public Biblioteca() {
		
	}
	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}
	
	
}
