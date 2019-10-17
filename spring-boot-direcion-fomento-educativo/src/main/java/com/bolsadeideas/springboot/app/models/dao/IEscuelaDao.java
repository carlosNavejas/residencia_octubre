package com.bolsadeideas.springboot.app.models.dao;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Escuela;

public interface IEscuelaDao extends PagingAndSortingRepository<Escuela, Long> {
	@Query("select p from Escuela p where p.claveescuela = ?1")
	public Escuela findByClaveescuela(String municipio);
}
