package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "cooperativas")
public class Cooperativa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clave_cooperatival;
	@NotEmpty
	@Column(length = 45)
	private String nombre_cooperativa;
	@NotEmpty
	@Column(length = 20)
	private String tipo;
	private Double monto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	@OneToOne(targetEntity = Inventario.class, mappedBy = "cooperativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Inventario inventario;

	@OneToOne(fetch = FetchType.LAZY)
	private Escuela escuela;
	@OneToMany(mappedBy = "cooperativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Socio> lista_socios;
	@OneToMany(mappedBy = "cooperativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ingresos_egresos> lista_ingresos_egresos;
	@OneToOne(mappedBy = "cooperativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Inventario intentario;

	@OneToMany(mappedBy = "cooperativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reparto_utilidades> lista_reparto;

	public List<Reparto_utilidades> getLista_reparto() {
		return lista_reparto;
	}

	public Inventario getIntentario() {
		return intentario;
	}

	public void setIntentario(Inventario intentario) {
		this.intentario = intentario;
	}

	public void setLista_reparto(List<Reparto_utilidades> lista_reparto) {
		this.lista_reparto = lista_reparto;
	}

	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
	}

	public Long getClave_cooperatival() {
		return clave_cooperatival;
	}

	public void setClave_cooperatival(Long clave_cooperatival) {
		this.clave_cooperatival = clave_cooperatival;
	}

	public String getNombre_cooperativa() {
		return nombre_cooperativa;
	}

	public void setNombre_cooperativa(String nombre_cooperativa) {
		this.nombre_cooperativa = nombre_cooperativa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Inventario getInventario() {
		return inventario;
	}

	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}

	public Cooperativa(Long clave_cooperatival, String nombre_cooperativa, String tipo, Double monto,
			Date fecha_registro, Inventario inventario) {

		this.clave_cooperatival = clave_cooperatival;
		this.nombre_cooperativa = nombre_cooperativa;
		this.tipo = tipo;
		this.monto = monto;
		this.fecha_registro = fecha_registro;
		this.inventario = inventario;
	}

	public Cooperativa() {
		lista_socios = new ArrayList<Socio>();
		lista_ingresos_egresos = new ArrayList<Ingresos_egresos>();
		lista_reparto = new ArrayList<Reparto_utilidades>();
	}

	public List<Ingresos_egresos> getLista_ingresos_egresos() {
		return lista_ingresos_egresos;
	}

	public void setLista_ingresos_egresos(List<Ingresos_egresos> lista_ingresos_egresos) {
		this.lista_ingresos_egresos = lista_ingresos_egresos;
	}

	public List<Socio> getLista_socios() {
		return lista_socios;
	}

	public void setLista_socios(List<Socio> lista_socios) {
		this.lista_socios = lista_socios;
	}

	@Override
	public String toString() {
		return "Cooperativa [clave_cooperatival=" + clave_cooperatival + ", nombre_cooperativa=" + nombre_cooperativa
				+ ", tipo=" + tipo + ", monto=" + monto + ", fecha_registro=" + fecha_registro + ", inventario="
				+ inventario + "]";
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public void addReparto(Reparto_utilidades e) {
		lista_reparto.add(e);
	}

}
