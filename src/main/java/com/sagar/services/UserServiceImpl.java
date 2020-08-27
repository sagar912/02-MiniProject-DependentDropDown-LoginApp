package com.sagar.services;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sagar.constants.AppConstants;
import com.sagar.entities.CityEntity;
import com.sagar.entities.CountryEntity;
import com.sagar.entities.StateEntity;
import com.sagar.entities.UserEntity;
import com.sagar.model.User;
import com.sagar.repositories.CityRepo;
import com.sagar.repositories.CountryRepo;
import com.sagar.repositories.StateRepo;
import com.sagar.repositories.UserRepo;
import com.sagar.utils.EmailUtils;
import com.sagar.utils.PwdUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private EmailUtils emailUtils;

//===========================================Save User =====================================================================//

	@Override
	public boolean saveUser(User user) {

		// ==== SETTING random password for user ========//
		user.setUserPwd(PwdUtils.generateRandomPassword(AppConstants.TEMP_PWD_LENGTH));

		// ==== SETTING CONSTANT VALUE IN USER=LOCKED========//
		user.setAccStatus(AppConstants.LOCKED_STR);

		UserEntity userEnity = new UserEntity();

		BeanUtils.copyProperties(user, userEnity);

		UserEntity savedEntity = userRepo.save(userEnity);

		if (savedEntity.getUserId() != null) {

			boolean sendUserAccUnlockEmail = emailUtils.SendUserAccUnlockEmail(user);

			return sendUserAccUnlockEmail;

		} else {

			return false;
		}

	}

//================================================================================================================//
	@Override
	public Map<Integer, String> getAllCountries() {

		Map<Integer, String> cmap = new LinkedHashMap<Integer, String>();
		List<CountryEntity> country = countryRepo.findAll();

		country.forEach(countryEntity -> {
			cmap.put(countryEntity.getCountryId(), countryEntity.getCountryName());

		});
		return cmap;
	}

//=============================================get states===================================================================//

	@Override
	public Map<Integer, String> getStatesByCountry(Integer cid) {

		Map<Integer, String> statemap = new LinkedHashMap<Integer, String>();
		List<StateEntity> statelist = stateRepo.findStateByCountryId(cid);

		statelist.forEach(stateEntity -> {

			statemap.put(stateEntity.getStateId(), stateEntity.getStateName());

		});

		return statemap;
	}

//===============================================get cities=================================================================//
	@Override
	public Map<Integer, String> getCitiesByStates(Integer sid) {

		Map<Integer, String> citymap = new LinkedHashMap<Integer, String>();

		List<CityEntity> citylist = cityRepo.findCityBystateId(sid);

		citylist.forEach(cityEntity -> {

			citymap.put(cityEntity.getCityId(), cityEntity.getCityName());

		});
		return citymap;
	}

//==================================================Email Validation =============================================================================//
	@Override
	public String findEmailByuserEmail(String userEmail) {
		UserEntity emailexixts = userRepo.findByuserEmail(userEmail);

		Integer userId = emailexixts.getUserId();
		if (userId == 0) {

			return "Unique";
		} else {

			return "Duplicate";

		}

	}

//==============================================================================================================================//
	@Override
	public UserEntity findByuserPwd(String xyz) {
		System.out.println(xyz);
		UserEntity userPwd = userRepo.findByuserPwd(xyz);
		if (userPwd != null) {

			userPwd.setAccStatus("Un-Locked");
		}
		return userPwd;
	}

//================================================================================================================================//
	@Override
	public boolean updateUser(UserEntity userEntity) throws Exception {

		// ==== SETTING Enncryptd  password for user ========//
		String userPwd = userEntity.getUserPwd();
		userEntity.setUserPwd(PwdUtils.encryptPassword(userPwd));

		UserEntity update = userRepo.save(userEntity);
		if (update.equals(null)) {
			return false;

		} else {
			return true;
		}
	}

//============================================================================================================================//

	@Override
	public UserEntity findByuserEmail(String userEmail) {

		System.out.println("Req email" + userEmail);

		UserEntity findByemailuser = userRepo.findByuserEmail(userEmail);
		System.out.println("Response email" + findByemailuser);
		if (findByemailuser.getUserId() != null) {
			boolean sendUserAccUnlockEmail = emailUtils.SendUserAccUnlockEmail2(findByemailuser);
			return findByemailuser;
		}
		return findByemailuser;
	}
//===========================================================================================================================//

	@Override
	public UserEntity findByuserEmailAndpassword(String userEmail) throws Exception {

		UserEntity checklogin = userRepo.findByuserEmailAndpassword(userEmail);
	
		System.out.println("checklogin before decryption"+checklogin);
		
	//	if (checklogin.getAccStatus() == "Un-Locked") {
			
			String UserPwd=checklogin.getUserPwd();
			
			checklogin.setUserPwd(PwdUtils.decryptPassword(UserPwd));

		//	System.out.println("true");
		//	if (checklogin.getUserEmail() == userEmail) {
			
			System.out.println("checklogin after decryption"+checklogin);

				return checklogin;
		//	} else {

		//		return null;

			//}

		//}
	}

}
