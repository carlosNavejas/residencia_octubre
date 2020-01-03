package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

public class RegionesEscuelas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String municipio;
	private int cantidadEscuelas;

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getCantidadEscuelas() {
		return cantidadEscuelas;
	}

	public void setCantidadEscuelas(int cantidadEscuelas) {
		this.cantidadEscuelas = cantidadEscuelas;
	}

	public RegionesEscuelas() {
	}

}
