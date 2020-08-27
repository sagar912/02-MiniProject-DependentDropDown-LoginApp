package com.sagar.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sagar.entities.UserEntity;

public interface UnlockRepo extends JpaRepository<UserEntity, Serializable>{
	

}
