package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.models.entity.Item_acervo;

public interface IItemAcervoDao extends CrudRepository<Item_acervo, Long> {

}
