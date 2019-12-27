package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Item_inventario;


public interface IIteminventarioDao extends PagingAndSortingRepository <Item_inventario, Long> {
	@Query("select p from Item_inventario p where p.cooperativa.clave_cooperatival=?1")
	public Page<Item_inventario> findItemByCooperativa(Long id, Pageable pageable);

}
