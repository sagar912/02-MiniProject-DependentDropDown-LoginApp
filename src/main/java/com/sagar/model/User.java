package com.sagar.model;


import lombok.Data;

@Data
public class User{

	
	private Integer userId;

	private Integer countryId;

	private Integer stateId;

	private Integer cityId;

	private String firstName;

	private String lastName;

	private String userEmail;

	private String gender;

	private String userPwd;

	private String accStatus;

	private String dob;

	private Long phoneNumber;
	
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;

}
