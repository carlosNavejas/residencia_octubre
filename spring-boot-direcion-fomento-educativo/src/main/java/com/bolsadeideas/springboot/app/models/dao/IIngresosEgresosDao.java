package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Ingresos_egresos;

public interface IIngresosEgresosDao  extends PagingAndSortingRepository<Ingresos_egresos, Long>{

	
	/*definir una consulta paginacion ingresos y egresos ordenados por fecha
	 * ordenados por cooperaqtiva y fecha bettewen
	 * 
	 * en si consultas mas personalizadas
	 * */
}
