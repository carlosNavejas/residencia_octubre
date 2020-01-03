package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "muebles")
public class Mueble implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String mueble;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Item_inventario item;
	
	public Item_inventario getItem() {
		return item;
	}



	public void setItem(Item_inventario item) {
		this.item = item;
	}



	public Long getId() {
		return id;
	}
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getMueble() {
		return mueble;
	}
	public void setMueble(String mueble) {
		this.mueble = mueble;
	}
	public Mueble(Long id, String mueble) {
		
		this.id = id;
		this.mueble = mueble;
	}
	public Mueble() {
		
	}
	

}
