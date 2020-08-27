package com.sagar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sagar.constants.AppConstants;
import com.sagar.entities.UserEntity;
import com.sagar.model.LoginUser;
import com.sagar.services.UserService;
import com.sagar.utils.PwdUtils;

@Controller
public class LoginController {

	@Autowired
	private UserService userservice;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//===============================================Load Form and Set Countries DropDown=================================================//	
	@GetMapping(value = { "/", "/login" })
	public String loadLoginPage(Model model) {
		LoginUser loginUser = new LoginUser();
		model.addAttribute("loginUser", loginUser);
		return AppConstants.LOGIN_PAGE;
	}
//==================================================== Submit login and password======================================================//

	@PostMapping(value = { "/userAccLogin" })
	public String submitBtn(LoginUser loginuser, Model model) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		String email = loginuser.getEmail();
		String password = loginuser.getPassword();
		System.out.println("user password"+password);
		try {
 

			UserEntity checklogin = userservice.findByuserEmailAndpassword(email);
			String accStatus = checklogin.getAccStatus();
			System.out.println("accstatus from db====>" + accStatus);

				if (accStatus.contains("Un-Locked")) {
						if (checklogin.getUserEmail().equals(email) && checklogin.getUserPwd().equals(password)) {
							model.addAttribute("Msg", "Welcome to UserApplication");
							logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
							return AppConstants.DASHBOARD_PAGE;

						} else {
							model.addAttribute("ErrorMsg", "In-valid Credentials");
							logger.debug(AppConstants.METHOD_EXECUTION_WARNING);
							return AppConstants.LOGIN_PAGE;
						}
				 }else {
					model.addAttribute("LockedMsg", "Account is Locked");
					logger.debug(AppConstants.METHOD_EXECUTION_WARNING);
					return AppConstants.LOGIN_PAGE;
	
				}

		} catch (Exception e) {
			e.getMessage();
			model.addAttribute("ErrorMsg", "In-valid Credentials");
           logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
   		    return AppConstants.LOGIN_PAGE;
		}
	
	}
	
}
