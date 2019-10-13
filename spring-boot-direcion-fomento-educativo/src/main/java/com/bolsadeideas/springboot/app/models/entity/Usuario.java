package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String curp;
	@Column(length = 40)
	@NotEmpty
	private String correo;
	@NotEmpty
	@Column(length = 8)
	private String contrasena;
	@NotEmpty
	@Column(length = 25)
	private String nombre;
	@NotEmpty
	@Column(length = 15)
	private String apellido_paterno;
	@NotEmpty
	@Column(length = 15)
	private String apellido_materno;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	@NotEmpty
	@Column(length = 2)
	private String estado;
	@OneToOne(targetEntity = Rol.class, fetch = FetchType.LAZY)
	private Rol rol_usuario;
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Escuela.class)
	private Escuela escuela;
}
