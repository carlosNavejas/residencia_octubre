package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Acervo_bibliografico;

public interface IAcervoDao extends PagingAndSortingRepository<Acervo_bibliografico, Long>{

}
