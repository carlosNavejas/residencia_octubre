package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;


import com.bolsadeideas.springboot.app.models.entity.Region;

public interface IRegionDao extends PagingAndSortingRepository<Region, Long> {

}
