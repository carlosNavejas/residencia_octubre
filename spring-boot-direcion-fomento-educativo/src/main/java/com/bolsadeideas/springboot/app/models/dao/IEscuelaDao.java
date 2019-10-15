package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Escuela;

public interface IEscuelaDao extends PagingAndSortingRepository<Escuela, Long>{

}
