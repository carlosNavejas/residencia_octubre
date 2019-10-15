package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_escuela;
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
	public Long getId_escuela() {
		return id_escuela;
	}
	public void setId_escuela(Long id_escuela) {
		this.id_escuela = id_escuela;
	}
	public String getClave_escuela() {
		return clave_escuela;
	}
	public void setClave_escuela(String clave_escuela) {
		this.clave_escuela = clave_escuela;
	}
	public String getNombre_escuela() {
		return nombre_escuela;
	}
	public void setNombre_escuela(String nombre_escuela) {
		this.nombre_escuela = nombre_escuela;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Biblioteca getBiblioteca() {
		return biblioteca;
	}
	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Cooperativa getCooperativa() {
		return cooperativa;
	}
	public void setCooperativa(Cooperativa cooperativa) {
		this.cooperativa = cooperativa;
	}
	public Escuela() {
		
	}
	public Escuela(Long id_escuela, String clave_escuela, String nombre_escuela, String tipo, String matricula,
			Biblioteca biblioteca, List<Usuario> usuarios, Cooperativa cooperativa) {
		super();
		this.id_escuela = id_escuela;
		this.clave_escuela = clave_escuela;
		this.nombre_escuela = nombre_escuela;
		this.tipo = tipo;
		this.matricula = matricula;
		this.biblioteca = biblioteca;
		this.usuarios = usuarios;
		this.cooperativa = cooperativa;
	}
	
	
	
	

}
