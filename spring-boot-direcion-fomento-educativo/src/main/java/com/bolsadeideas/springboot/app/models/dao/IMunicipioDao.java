package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Municipio;


public interface IMunicipioDao extends CrudRepository<Municipio, Long> {
/*
	
	@Query("select m from Municipio m join fetch m.region r")
	public List<Municipio> fechtByMunicipioWhithMunicioiWhitiRegion(String municipio);*/

}
