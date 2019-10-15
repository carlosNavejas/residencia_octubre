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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "inventarios")
public class Inventario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clave_inventario;
	@Column(length = 100)
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;

	@OneToOne(targetEntity = Cooperativa.class, fetch = FetchType.LAZY)
	private Cooperativa cooperativa;

	public Long getClave_inventario() {
		return clave_inventario;
	}

	public void setClave_inventario(Long clave_inventario) {
		this.clave_inventario = clave_inventario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Cooperativa getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}

	public Inventario() {
		
	}

	public Inventario(Long clave_inventario, String descripcion, Date fecha_registro, Cooperativa cooperativa) {
		
		this.clave_inventario = clave_inventario;
		this.descripcion = descripcion;
		this.fecha_registro = fecha_registro;
		this.cooperativa = cooperativa;
	}

	
	// Estos valores pueden ser calculables al momento de generar el reporte
//private String ciclo_escolar; @Column(length = 45)
//	private String periodo_escolar;

}
