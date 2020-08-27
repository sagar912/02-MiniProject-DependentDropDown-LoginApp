package com.sagar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sagar.model.User;
import com.sagar.services.UserService;

@Controller
public class forgotPasswordController {

	@Autowired
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(forgotPasswordController.class);

//=====================================================//
	@GetMapping(value = { "/forgotPassword" })
	public String loadForm(User user, Model model) {

		return AppConstants.FORGOT_PASSWORD_PAGE;
	}

//=========================================================//	
	@PostMapping(value = { "/forgotPasswords" })
	public String loadForm1(@ModelAttribute("user") User user, Model model) {

		logger.debug(AppConstants.METHOD_EXECUTION_START);
		try {
			String email = user.getUserEmail();
			UserEntity findByemail = service.findByuserEmail(email);
			model.addAttribute("forgotPasswordMsg", "Your Password is sent to your registered email ");
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);

		} catch (Exception e) {
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
			model.addAttribute("ErrorforgotPasswordMsg", "Entered Email is Wrong... ");
			return "false";
		}
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);
		return AppConstants.FORGOT_PASSWORD_PAGE;

	}

}
