package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
	@OneToOne(targetEntity = Cooperativa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Escuela escuela;
}
