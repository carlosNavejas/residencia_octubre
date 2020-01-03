package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Cooperativa;

public interface ICooperativaDao extends CrudRepository<Cooperativa,Long>{

	@Query("select COUNT(p.cooperativa.clave_cooperatival) from Escuela p where p.direccion.municipio.region.id = ?1")
	public int findCountCooperativasByEscuelaRegion(Long municipio);
}
