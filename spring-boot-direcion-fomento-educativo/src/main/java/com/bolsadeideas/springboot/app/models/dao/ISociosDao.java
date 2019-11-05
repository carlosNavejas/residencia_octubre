package com.bolsadeideas.springboot.app.models.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.bolsadeideas.springboot.app.models.entity.Socio;

public interface ISociosDao extends PagingAndSortingRepository<Socio, Long> {
	@Query("select f from Socio where f.curp=?1")
	public Socio fetchByCurp(String curp_socio);
}
