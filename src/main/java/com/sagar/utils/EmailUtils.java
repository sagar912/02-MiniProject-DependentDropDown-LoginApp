package com.sagar.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sagar.constants.AppConstants;
import com.sagar.entities.UserEntity;
import com.sagar.model.User;

@Component
public class EmailUtils {

	//========================================= Sending Email For Temporary Password ============================================//
	@Autowired
	private JavaMailSender javaMailSender;
	private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);

	public boolean SendUserAccUnlockEmail(User user) {
        logger.debug(AppConstants.METHOD_EXECUTION_START);

		try {

			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);

			helper.setTo(user.getUserEmail());
			helper.setSubject("Unlock User Account");
			helper.setText(getUnlockAccEmailBody(user), true);
			javaMailSender.send(mimeMsg);
			logger.info(AppConstants.METHOD_EXECUTION_SUCCESSFUL);
			return true;
		} catch (Exception e) {
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
			logger.debug(AppConstants.METHOD_EXECUTION_ENDED);
			return false;

		}
	}

	private String getUnlockAccEmailBody(User account) throws IOException {
        logger.debug(AppConstants.METHOD_EXECUTION_START);
		StringBuffer sb = new StringBuffer("");
		FileReader fr = new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE.txt");		
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		
		br.close();
		
		String mailBody = sb.toString();
		mailBody = mailBody.replace("{FNAME}", account.getFirstName());
		mailBody = mailBody.replace("{LNAME}", account.getLastName());
		mailBody = mailBody.replace("{TEMP-PWD}",account.getUserPwd());
		mailBody = mailBody.replace("{EMAIL}", account.getUserEmail());
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);
		return mailBody;
	}
//=========================== Sending Email For Forgot Password =================================================================//
	public boolean SendUserAccUnlockEmail2(UserEntity findByemailuser) {
		
		logger.debug(AppConstants.METHOD_EXECUTION_START);
		try {

			MimeMessage mimeMsg = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);

			helper.setTo(findByemailuser.getUserEmail());
			helper.setSubject("Unlock User Account");
			helper.setText(getUnlockAccEmailBody2(findByemailuser), true);
			
			javaMailSender.send(mimeMsg);

			return true;
		} catch (Exception e) {
			logger.error(AppConstants.METHOD_EXECUTION_EXCEPTION);
		}
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);
		return false;
	}

	private String getUnlockAccEmailBody2(UserEntity findByemailuser) throws IOException {

		logger.debug(AppConstants.METHOD_EXECUTION_START);

		StringBuffer sb = new StringBuffer("");

		FileReader fr = new FileReader("UNLOCK-ACC-EMAIL-BODY-TEMPLATE2.txt");		
		
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();

		while (line != null) {
			sb.append(line);
			line = br.readLine();
		}
		
		br.close();
		
		String mailBody = sb.toString();
		mailBody = mailBody.replace("{FNAME}", findByemailuser.getFirstName());
		mailBody = mailBody.replace("{LNAME}", findByemailuser.getLastName());
		mailBody = mailBody.replace("{TEMP-PWD}",findByemailuser.getUserPwd());
		mailBody = mailBody.replace("{EMAIL}", findByemailuser.getUserEmail());
		
		logger.debug(AppConstants.METHOD_EXECUTION_ENDED);

		return mailBody;
		
	}
	}

