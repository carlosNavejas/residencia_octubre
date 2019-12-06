package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Socio;

public interface ISociosDao extends PagingAndSortingRepository<Socio, Long> {
	@Query("select p from Socio p where p.cooperativa.clave_cooperatival=?1")
	public Page<Socio> findByCooperativa(Long id, Pageable pageable);

	@Query("select p from Socio p where p.cooperativa.clave_cooperatival=?1")
	public List<Socio> findByCooperativaa(Long id);

	/*
	 * select count(id_socio) from socios where cooperativa_clave_cooperatival=1;
	 */
	@Query("select COUNT(p.id_socio) from Socio p where p.cooperativa.clave_cooperatival=?1 and p.estado='ACTIVO'")
	public int countSociosByCooperativa(Long id);

}
