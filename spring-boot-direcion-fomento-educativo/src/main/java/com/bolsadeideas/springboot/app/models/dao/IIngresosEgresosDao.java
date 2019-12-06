package com.bolsadeideas.springboot.app.models.dao;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;

public interface IIngresosEgresosDao extends PagingAndSortingRepository<Ingresos_egresos, Long> {

	/*
	 * definir una consulta paginacion ingresos y egresos ordenados por fecha
	 * ordenados por cooperaqtiva y fecha bettewen
	 * 
	 * en si consultas mas personalizadas
	 */

	@Query("select p from Ingresos_egresos p where p.cooperativa.clave_cooperatival=?1 and p.tipoRegistro='Inicio Curso'  ORDER BY p.fecharegistro DESC")
	public Page<Ingresos_egresos> findIngresosByCooperativa(Long id, Pageable pageable);

	@Query(value = "select t from Ingresos_egresos t where t.cooperativa.clave_cooperatival=?1 and t.fecharegistro BETWEEN ?2  AND ?3 order by t.fecharegistro asc")
	public Page<Ingresos_egresos> findIngresosBetweenCiclo(@Param("id") Long id, @Param("dateFrom") Date dateFrom,
			@Param("dateEnd") Date dateEnd, Pageable pageable);
}
