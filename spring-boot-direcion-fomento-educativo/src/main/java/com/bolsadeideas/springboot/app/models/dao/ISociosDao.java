package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;






import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.bolsadeideas.springboot.app.models.entity.Socio;

public interface ISociosDao extends PagingAndSortingRepository<Socio, Long> {
	@Query("select p from Socio p where p.cooperativa.clave_cooperatival=?1")
	public Page<Socio> findByCooperativa(Long id,Pageable pageable);
	@Query("select p from Socio p where p.cooperativa.clave_cooperatival=?1")
	public List<Socio> findByCooperativaa(Long id);
}
