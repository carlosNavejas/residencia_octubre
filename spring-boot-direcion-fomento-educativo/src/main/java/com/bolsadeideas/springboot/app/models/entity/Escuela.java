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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@Column(length = 10, name = "clave_escuela")
	private String claveescuela;
	@NotEmpty
	@Column(length = 45)
	private String nombre_escuela;
	@Column(length = 15)
	private String telefono;
	@Column(length = 15)
	private String turno;

	@Column(length = 35)
	private String nombre_dir;
	@Column(length = 15)
	private String apellido_pdir;
	@Column(length = 15)
	private String apellido_mdir;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY, targetEntity = Direccion.class, cascade = CascadeType.ALL)
	private Direccion direccion;

	@NotEmpty
	@Column(length = 15)
	private String tipo;
	@Column(length = 6)
	private String matricula;
	@OneToOne(targetEntity = Biblioteca.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH, mappedBy = "escuela")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Biblioteca biblioteca;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(targetEntity = Usuario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "escuela")
	private List<Usuario> usuarios;

	@OneToOne(mappedBy = "escuela", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cooperativa cooperativa;

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Long getId_escuela() {
		return id_escuela;
	}

	public void setId_escuela(Long id_escuela) {
		this.id_escuela = id_escuela;
	}

	public String getClaveescuela() {
		return claveescuela;
	}

	public void setClaveescuela(String claveescuela) {
		this.claveescuela = claveescuela;
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
		cooperativa = null;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombre_dir() {
		return nombre_dir;
	}

	public void setNombre_dir(String nombre_dir) {
		this.nombre_dir = nombre_dir;
	}

	public String getApellido_pdir() {
		return apellido_pdir;
	}

	public void setApellido_pdir(String apellido_pdir) {
		this.apellido_pdir = apellido_pdir;
	}

	public String getApellido_mdir() {
		return apellido_mdir;
	}

	public void setApellido_mdir(String apellido_mdir) {
		this.apellido_mdir = apellido_mdir;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Escuela(Long id_escuela, String claveescuela, String nombre_escuela, String telefono, String turno,
			String nombre_dir, String apellido_pdir, String apellido_mdir, Direccion direccion, String tipo,
			String matricula, Biblioteca biblioteca, List<Usuario> usuarios, Cooperativa cooperativa) {
		super();
		this.id_escuela = id_escuela;
		this.claveescuela = claveescuela;
		this.nombre_escuela = nombre_escuela;
		this.telefono = telefono;
		this.turno = turno;
		this.nombre_dir = nombre_dir;
		this.apellido_pdir = apellido_pdir;
		this.apellido_mdir = apellido_mdir;
		this.direccion = direccion;
		this.tipo = tipo;
		this.matricula = matricula;
		this.biblioteca = biblioteca;
		this.usuarios = usuarios;
		this.cooperativa = cooperativa;
	}

}
