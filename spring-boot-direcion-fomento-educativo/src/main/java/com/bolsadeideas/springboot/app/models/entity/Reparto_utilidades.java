package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

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

@Entity
@Table(name = "reparto_utilidades")
public class Reparto_utilidades implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_reparto;
	private int numeroSocios;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	private double fondoRepartible;
	private double cantidadPorSocio;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Cooperativa.class)
	private Cooperativa cooperativa;

	public Long getId_reparto() {
		return id_reparto;
	}

	public void setId_reparto(Long id_reparto) {
		this.id_reparto = id_reparto;
	}

	public int getNumeroSocios() {
		return numeroSocios;
	}

	public void setNumeroSocios(int numeroSocios) {
		this.numeroSocios = numeroSocios;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public double getFondoRepartible() {
		return fondoRepartible;
	}

	public void setFondoRepartible(double fondoRepartible) {
		this.fondoRepartible = fondoRepartible;
	}

	public double getCantidadPorSocio() {
		return cantidadPorSocio;
	}

	public void setCantidadPorSocio(double cantidadPorSocio) {
		this.cantidadPorSocio = cantidadPorSocio;
	}



	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	@PrePersist
	public void iniciaFecha() {
		fechaRegistro = new Date();
	}

	public Reparto_utilidades() {

	}

}
