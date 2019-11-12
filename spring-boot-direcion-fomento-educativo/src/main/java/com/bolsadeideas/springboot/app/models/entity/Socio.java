package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "socios")
public class Socio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_socio;
	@NotEmpty
	@Column(length = 20)
	private String nombre;
	@NotEmpty
	@Column(length = 20)
	private String apellido_p;
	@NotEmpty
	@Column(length = 20)
	private String apellido_m;
	@Column(length = 10)
	private String grado;
	@Column(length = 10)
	private String grupo;
	@Column(length = 30)
	private String titular;
	private double aproteInicial;
	@Column(length = 12)
	private String estado;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;

	@ManyToOne(fetch = FetchType.LAZY)
	private Cooperativa cooperativa;

	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Long getId_socio() {
		return id_socio;
	}

	public void setId_socio(Long id_socio) {
		this.id_socio = id_socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_p() {
		return apellido_p;
	}

	public void setApellido_p(String apellido_p) {
		this.apellido_p = apellido_p;
	}

	public String getApellido_m() {
		return apellido_m;
	}

	public void setApellido_m(String apellido_m) {
		this.apellido_m = apellido_m;
	}

	public String getGrado() {
		return grado;
	}

	public void setGrado(String grado) {
		this.grado = grado;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public double getAproteInicial() {
		return aproteInicial;
	}

	public void setAproteInicial(double aproteInicial) {
		this.aproteInicial = aproteInicial;
	}

	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	public Socio() {

	}

}
