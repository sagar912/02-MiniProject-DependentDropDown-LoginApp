package com.sagar.utils;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sagar.constants.AppConstants;
import com.sagar.controller.forgotPasswordController;

public class PwdUtils {

	private static final String SECRET_KEY = "abc123xyz123abcd";
	private static final String INIT_VECTOR = "abc123xyz123abcd";
	
	private final static String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final Logger logger = LoggerFactory.getLogger(PwdUtils.class);

//====================================== Random Temporary Password =================================================//	
	
	public static String generateRandomPassword(int len)
	{
        logger.debug(AppConstants.METHOD_EXECUTION_START);
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < len; i++) {
			int randomIndex = random.nextInt(ALPHA_NUMERIC_STRING.length());
			sb.append(ALPHA_NUMERIC_STRING.charAt(randomIndex));
		}
        logger.debug(AppConstants.METHOD_EXECUTION_ENDED);
		return sb.toString();
	}
//==========================================Generate  Encrypted Password ======================================//
	
	public static String encryptPassword(String UserPwd) throws Exception {
		IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());
		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamSpec);

		byte[] encrypted = cipher.doFinal(UserPwd.getBytes());

		return Base64.getEncoder().encodeToString(encrypted);
	}

//========================================= Decrypted Password ======================================//
	
	public static String decryptPassword(String UserPwd) throws Exception {
		IvParameterSpec ivParamSpec = new IvParameterSpec(INIT_VECTOR.getBytes());

		SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes("UTF-8"), "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamSpec);

		byte[] decodedMsg = Base64.getDecoder().decode(UserPwd);

		byte[] decrypted = cipher.doFinal(decodedMsg);

		return new String(decrypted);

	}
}
