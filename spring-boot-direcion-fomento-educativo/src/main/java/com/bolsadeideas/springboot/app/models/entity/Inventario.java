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

	
	// Estos valores pueden ser calculables al momento de generar el reporte
//private String ciclo_escolar; @Column(length = 45)
//	private String periodo_escolar;

}
