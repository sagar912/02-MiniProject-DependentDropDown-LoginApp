package com.sagar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sagar.constants.AppConstants;
import com.sagar.entities.UserEntity;
import com.sagar.model.UnlockAccount;
import com.sagar.model.User;
import com.sagar.services.UserService;

@Controller
public class UnlockLoginController {

	@Autowired
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(UnlockLoginController.class);

	// =================================== save changed password and unlock account //========================================//

	@PostMapping(value = { "/unlockAcc" })
	public String unlockUser(@ModelAttribute("unlockAcc") UnlockAccount acc, Model model) {
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		User user = new User();
		String newPwd = acc.getTempPwd();
		logger.info("temporary password returned", newPwd);
		BeanUtils.copyProperties(acc, user);
		try {
			UserEntity userEntity = service.findByuserPwd(newPwd);
			if (newPwd.equals(userEntity.getUserPwd())) {
				userEntity.setUserPwd(acc.getNewPwd());
				logger.info("set password", userEntity);

			}
			// ======================= Set Permanent Password =======================//

			boolean updateUser = service.updateUser(userEntity);

			if (updateUser == true) {
				model.addAttribute("SuccessMsg", "Your Registration is Successful");
				logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);

			} else if (updateUser == false) {
				logger.warn(AppConstants.METHOD_EXECUTION_WARNING);
				return AppConstants.UNLOCK_USER_PAGE;

			}
			return AppConstants.UNLOCK_USER_SUCCESS_MSG_PAGE;

		} catch (Exception e) {
			model.addAttribute("ExMsg", "Account is not unlock due to incorrect temp pwd");
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION, e.getMessage());
			return AppConstants.UNLOCK_USER_PAGE;
		}

	}
}