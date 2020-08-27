package com.sagar.services;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.sagar.entities.CountryEntity;
import com.sagar.entities.UserEntity;
import com.sagar.model.User;

@Service
public interface UserService {

	boolean saveUser(User user);

	Map<Integer, String> getAllCountries();

	Map<Integer, String> getStatesByCountry(Integer cid);

	Map<Integer, String> getCitiesByStates(Integer sid);

	String findEmailByuserEmail(String userEmail);

	UserEntity findByuserPwd(String xyz);

	boolean updateUser(UserEntity userEntity) throws Exception;

	UserEntity findByuserEmail(String userEmail);

	UserEntity findByuserEmailAndpassword(String userEmail) throws Exception;

}
