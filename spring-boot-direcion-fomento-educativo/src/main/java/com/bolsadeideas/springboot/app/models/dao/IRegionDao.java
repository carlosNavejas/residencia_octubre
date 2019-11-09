package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;



import com.bolsadeideas.springboot.app.models.entity.Region;

public interface IRegionDao extends CrudRepository<Region, Long> {

}
