package com.sagar.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sagar.entities.UserEntity;


public interface UserRepo extends JpaRepository<UserEntity, Serializable>{

	 @Query(value = "SELECT USER_EMAIL FROM user_master WHERE USER_EMAIL =:userEmail",nativeQuery = true)
	 String findEmailByuserEmail(String userEmail);
	
	 UserEntity findByuserPwd(String xyz);
	
	 UserEntity findByuserEmail(String userEmail);

	 @Query(value = "SELECT * FROM user_master WHERE USER_EMAIL =:userEmail",nativeQuery = true)
	 UserEntity findByuserEmailAndpassword(String userEmail);
	 	
	


}
