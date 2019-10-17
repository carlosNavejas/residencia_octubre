package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.bolsadeideas.springboot.app.models.entity.Municipio;


public interface IMunicipioDao extends CrudRepository<Municipio, Long> {
	@Query("select p from Municipio p where p.region.id like %?1%")
	public List<Municipio> findByMunicipioRegionId(String municipio);
	
	
	public List<Municipio> findByMunicipioLikeIgnoreCase(String mun);
	
	
}
