package com.bolsadeideas.springboot.app.models.dao;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.bolsadeideas.springboot.app.models.entity.Reparto_utilidades;


public interface IRepartoutilidadesDao extends PagingAndSortingRepository<Reparto_utilidades, Long> {
	@Query("select p from Reparto_utilidades p where p.cooperativa.clave_cooperatival=?1")
	public Page<Reparto_utilidades> findByCooperativaId(Long id,Pageable pageable);

}
