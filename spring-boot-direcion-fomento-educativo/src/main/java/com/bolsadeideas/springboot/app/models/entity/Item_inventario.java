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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "item_inventario")
public class Item_inventario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_item_inventario;
	private Integer cantidad;
	@NotEmpty
	@Column(length = 15)
	private String estado;
	@ManyToOne(targetEntity = Mueble.class, fetch = FetchType.LAZY)
	private Mueble mueble;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	public Long getId_item_inventario() {
		return id_item_inventario;
	}
	public void setId_item_inventario(Long id_item_inventario) {
		this.id_item_inventario = id_item_inventario;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Mueble getMueble() {
		return mueble;
	}
	public void setMueble(Mueble mueble) {
		this.mueble = mueble;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
}
