package com.sagar.entities;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name="USER_MASTER")
@Data
public class UserEntity {

	@Id
	@SequenceGenerator(name = "user", sequenceName = "user_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "user", strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "COUNTRY_ID")
	private Integer countryId;

	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "CITY_ID")
	private Integer cityId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "USER_EMAIL")
	private String userEmail;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "USER_PWD")
	private String userPwd;

	@Column(name = "ACC_STATUS")
	private String accStatus;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "PHONE_NUMBER")
	private long phoneNumber;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

		
}
