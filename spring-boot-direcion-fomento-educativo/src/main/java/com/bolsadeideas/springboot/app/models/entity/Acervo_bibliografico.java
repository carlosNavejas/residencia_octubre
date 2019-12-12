package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "acervos_bibliograficos")
public class Acervo_bibliografico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_acervo;
	@Temporal(TemporalType.TIME)
	private Date fechaRegistro;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "acervo_id")
	List<Item_acervo> listaAcervo;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Biblioteca biblioteca;

	

	public Biblioteca getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(Biblioteca biblioteca) {
		this.biblioteca = biblioteca;
	}

	public void setId_acervo(Long id_acervo) {
		this.id_acervo = id_acervo;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setListaAcervo(List<Item_acervo> listaAcervo) {
		this.listaAcervo = listaAcervo;
	}
	public List<Item_acervo> getListaAcervo() {
		return listaAcervo;
	}
	public Long getId_acervo() {
		return id_acervo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	

	public Acervo_bibliografico() {
		listaAcervo = new ArrayList<Item_acervo>();
	}

	public void agregarItem(Item_acervo item) {
		listaAcervo.add(item);
	}

	@PrePersist
	public void iniciarFecha() {
		fechaRegistro = new Date();
	}

}
