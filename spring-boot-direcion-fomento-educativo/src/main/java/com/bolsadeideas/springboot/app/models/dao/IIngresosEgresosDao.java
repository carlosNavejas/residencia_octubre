package com.bolsadeideas.springboot.app.models.dao;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;


public interface IIngresosEgresosDao  extends PagingAndSortingRepository<Ingresos_egresos, Long>{

	
	/*definir una consulta paginacion ingresos y egresos ordenados por fecha
	 * ordenados por cooperaqtiva y fecha bettewen
	 * 
	 * en si consultas mas personalizadas
	 * */
	
	 //@Query("select a from Article a where a.creationDateTime <= :creationDateTime")
	//@Query("select p from Socio p where p.cooperativa.clave_cooperatival=?1")
	//public Page<Ingresos_egresos> findByIngresosEgresosByciclo(Long id,Date fechaInicio,Date fechaDin,Pageable pageable);
}
