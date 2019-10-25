package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "municipios")
public class Municipio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_municipio;
	@NotEmpty
	@Column(length = 40)
	private String municipio;
	
	@ManyToOne(targetEntity = Region.class,fetch =FetchType.LAZY )
	private Region region;

	public Long getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(Long id_municipio) {
		this.id_municipio = id_municipio;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Municipio() {
		
	}

	@Override
	public String toString() {
		return "Municipio [id_municipio=" + id_municipio + ", municipio=" + municipio + ", region=" + region + "]";
	}
	
	
	

}
