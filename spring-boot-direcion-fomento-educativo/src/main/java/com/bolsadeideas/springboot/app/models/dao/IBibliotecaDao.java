package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;

public interface IBibliotecaDao extends CrudRepository<Biblioteca, Long> {
	@Query("select COUNT(p.biblioteca.clave_biblioteca) from Escuela p where p.direccion.municipio.region.id = ?1")
	public int findCountBibliotecasByEscuelaRegion(Long municipio);
}
