package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Asignatura;

public interface IAsignaturaDao extends CrudRepository<Asignatura, Long> {

}
