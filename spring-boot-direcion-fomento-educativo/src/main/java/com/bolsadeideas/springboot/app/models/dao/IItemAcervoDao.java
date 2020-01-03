package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Item_acervo;

public interface IItemAcervoDao extends PagingAndSortingRepository<Item_acervo, Long> {
	@Query("select p from Item_acervo p where p.biblioteca.clave_biblioteca=?1")
	public Page<Item_acervo> findItemByBiblioteca(Long id, Pageable pageable);

	@Query("select p from Item_acervo p where p.biblioteca.clave_biblioteca=?1")
	public List<Item_acervo> findItemAcervoByBiblioteca(Long id);
}
