package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * @author TOSHIBA
 *
 */
@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotEmpty
	@Column(length = 35)
	private String calle;
	@Column(length = 4)
	@NotEmpty
	private String numero;
	@ManyToOne(targetEntity = Municipio.class,fetch = FetchType.LAZY)
	private Municipio municipio;
	@OneToOne(targetEntity = Escuela.class)
	private Escuela escuela;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Direccion(Long id, String calle, String numero, Municipio municipio) {

		this.id = id;
		this.calle = calle;
		this.numero = numero;
		this.municipio = municipio;
	}

	public Direccion() {

	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

}
