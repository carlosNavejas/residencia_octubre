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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.PrePersist;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	@Column(length = 19, unique = true)
	private String curp;
	@Column(length = 50, unique = true)
	@NotEmpty
	private String correo;
	@NotEmpty
	@Column(length = 65)
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
	private boolean estado;

	@OneToMany(targetEntity = Rol.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_idd")
	private List<Rol> roles;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Escuela.class)
	private Escuela escuela;

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Escuela getEscuela() {
		return escuela;
	}

	public void setEscuela(Escuela escuela) {
		this.escuela = escuela;
	}

	public Usuario() {
		estado = true;
		roles = new ArrayList<Rol>();
	}

	public void agregarRol(Rol rolinCanallin) {
		roles.add(rolinCanallin);
	}

	@PrePersist
	public void prePersist() {
		fecha_registro = new Date();
		estado = true;
	}

	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", curp=" + curp + ", correo=" + correo + ", contrasena="
				+ contrasena + ", nombre=" + nombre + ", apellido_paterno=" + apellido_paterno + ", apellido_materno="
				+ apellido_materno + ", fecha_registro=" + fecha_registro + ", estado=" + estado + ", roles=" + roles
				+ ", escuela=" + escuela + "]";
	}

	public String obtenerRol() {

		String []roless=roles.get(0).getNombrerol().split("_");
		
		return roless[1];
	}

}
