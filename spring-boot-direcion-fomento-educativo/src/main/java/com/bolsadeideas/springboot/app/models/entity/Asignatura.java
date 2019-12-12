package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_asignatura;
	@Column(length = 50)
	private String asignatura;
	
	
	public Long getId_asignatura() {
		return id_asignatura;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setId_asignatura(Long id_asignatura) {
		this.id_asignatura = id_asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public Asignatura() {
		
	}
	
	
	

}
