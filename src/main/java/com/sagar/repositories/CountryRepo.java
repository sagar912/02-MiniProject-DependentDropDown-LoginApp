package com.sagar.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.entities.CountryEntity;

public interface CountryRepo extends JpaRepository<CountryEntity, Serializable> {

	
}
