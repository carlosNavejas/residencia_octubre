package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Biblioteca;

public interface IBibliotecaDao extends CrudRepository<Biblioteca, Long>{

}
