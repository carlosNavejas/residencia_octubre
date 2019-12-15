package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Mueble;


public interface IMueblesDao extends CrudRepository<Mueble, Long>{
	@Query("select u from Mueble u where u.mueble=?1")
	public Mueble findByMueble(String correo);
}
