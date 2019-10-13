package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "escuelas")
public class Escuela implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 10)
	private String clave_escuela;
	@NotEmpty
	@Column(length = 45)
	private String nombre_escuela;
	@NotEmpty
	@Column(length = 15)
	private String tipo;
	@Column(length = 6)
	private String matricula;
	@OneToOne(targetEntity = Biblioteca.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "escuela")
	private Biblioteca biblioteca;
	@OneToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "escuela")
	private List<Usuario> usuarios;
	@OneToOne(targetEntity = Cooperativa.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "escuela")
	private Cooperativa cooperativa;
}
