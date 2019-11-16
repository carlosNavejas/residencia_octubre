package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ingresos_egresos")
public class Ingresos_egresos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_ingreosegreos;

	private String ciclo;
	private Date fecharegistro;
	private Double montoinicial;
	private Double egresodelperiodo;
	private Double ingresodelperiodo;
	private Double fondosocial;
	private Double fondoreserva;
	private Double fondorepartible;
	private String tipoRegistro;
	private String archivolista_socios;

	public String getTipoRegistro() {
		return tipoRegistro;
	}

	public String getArchivolista_socios() {
		return archivolista_socios;
	}

	public void setArchivolista_socios(String archivolista_socios) {
		this.archivolista_socios = archivolista_socios;
	}

	public void setTipoRegistro(String tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	private Cooperativa cooperativa;

	public Long getId_ingreosegreos() {
		return id_ingreosegreos;
	}

	public void setId_ingreosegreos(Long id_ingreosegreos) {
		this.id_ingreosegreos = id_ingreosegreos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Date getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(Date fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Double getMontoinicial() {
		return montoinicial;
	}

	public void setMontoinicial(Double montoinicial) {
		this.montoinicial = montoinicial;
	}

	public Double getEgresodelperiodo() {
		return egresodelperiodo;
	}

	public void setEgresodelperiodo(Double egresodelperiodo) {
		this.egresodelperiodo = egresodelperiodo;
	}

	public Double getIngresodelperiodo() {
		return ingresodelperiodo;
	}

	public void setIngresodelperiodo(Double ingresodelperiodo) {
		this.ingresodelperiodo = ingresodelperiodo;
	}

	public Double getFondosocial() {
		return fondosocial;
	}

	public void setFondosocial(Double fondosocial) {
		this.fondosocial = fondosocial;
	}

	public Double getFondoreserva() {
		return fondoreserva;
	}

	public void setFondoreserva(Double fondoreserva) {
		this.fondoreserva = fondoreserva;
	}

	public Double getFondorepartible() {
		return fondorepartible;
	}

	public void setFondorepartible(Double fondorepartible) {
		this.fondorepartible = fondorepartible;
	}

	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	public Ingresos_egresos() {

	}

}
