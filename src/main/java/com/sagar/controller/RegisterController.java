package com.sagar.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sagar.constants.AppConstants;
import com.sagar.entities.UserEntity;
import com.sagar.model.UnlockAccount;
import com.sagar.model.User;
import com.sagar.services.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

//===============================================Load Form and Set Countries DropDown=================================================//	
	@GetMapping(value = { "/register" })
	public String loadForm(Model model) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		User user = new User();
		try {
			Map<Integer, String> countrieslist = service.getAllCountries();
			model.addAttribute("countries", countrieslist);
			model.addAttribute("user", user);
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
		} catch (Exception e) {

			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
		}
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);

		return AppConstants.REGISTER_PAGE;
	}

//============================================Set States DropDown =====================================================//	
	@GetMapping(value = { "/getStates" })
	@ResponseBody
	public Map<Integer, String> getStates(@RequestParam("cid") Integer countryId) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);

		try {
			Map<Integer, String> statesByCountry = service.getStatesByCountry(countryId);
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
			return statesByCountry;

		} catch (Exception e) {
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
			return null;
		}

	}

//================================================ Set City DropDown =================================================//

	@GetMapping(value = { "/getCities" })
	@ResponseBody
	public Map<Integer, String> getCities(@RequestParam("stateid") Integer stateId) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		try {
			Map<Integer, String> citiesByState = service.getCitiesByStates(stateId);
			System.out.println(citiesByState.toString());
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
			return citiesByState;
		} catch (Exception e) {
			e.getMessage();
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
			return null;

		}

	}
//=============================================== Email Validation ====================================================//

	@GetMapping("/validateEmail")
	public @ResponseBody String getEmail(@RequestParam("checkemail") String emailid) {

		logger.debug(AppConstants.METHOD_EXECUTION_START);
		try {

			String emailexists = service.findEmailByuserEmail(emailid);
			System.out.println("emailexists==>" + emailexists);
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);

		} catch (Exception e) {

			e.getMessage();
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);

			return "false";

		}
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);

		return service.findEmailByuserEmail(emailid);

	}

//=======================================================================================//
	@PostMapping(value = { "/userAccReg" })
	public String saveUser(User user, Model model) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		user.setAccStatus("Locked");
		try {

			boolean saveUser = service.saveUser(user);

			System.out.println(saveUser);
			
			if (saveUser) {
				String SuccessMsg = "Your registration is almost completed.Please check your email to unlock your account";
				model.addAttribute("SuccessMsg", SuccessMsg);
				logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
				return "SuccessReg";

			}
		} catch (Exception e) {
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
			e.getMessage();
			return null;
		}
		return "SuccessReg";


	}

//=================================================== Unlock Email =====================================================//
	@GetMapping(value = { "/unlockAcc" })
	public String unlockUser(@RequestParam("email") String email, Model model) {
		model.addAttribute("email", email);
		UnlockAccount unlockAcc = new UnlockAccount();
		model.addAttribute("unlockAcc", unlockAcc);

		return AppConstants.UNLOCK_USER_PAGE;
	}

}
