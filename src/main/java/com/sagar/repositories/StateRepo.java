package com.sagar.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sagar.entities.StateEntity;

public interface StateRepo extends JpaRepository<StateEntity, Serializable>{
	
	public List<StateEntity> findStateByCountryId(Integer countryId);

}
