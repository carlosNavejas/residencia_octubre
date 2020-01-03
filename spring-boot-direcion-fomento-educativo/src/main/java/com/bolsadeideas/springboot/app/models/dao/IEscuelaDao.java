package com.bolsadeideas.springboot.app.models.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Escuela;

public interface IEscuelaDao extends PagingAndSortingRepository<Escuela, Long> {
	@Query("select p from Escuela p where p.claveescuela = ?1")
	public Escuela findByClaveescuela(String municipio);
	
	
	
	@Query("select COUNT(p.id_escuela) from Escuela p where p.direccion.municipio.region.id = ?1")
	public int findEscuelaByRegion(Long municipio);
}
