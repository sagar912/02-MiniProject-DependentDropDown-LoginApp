package com.sagar.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.entities.CityEntity;

public interface CityRepo extends JpaRepository<CityEntity, Serializable> {
	
	public List<CityEntity> findCityBystateId(Integer stateId);


}
