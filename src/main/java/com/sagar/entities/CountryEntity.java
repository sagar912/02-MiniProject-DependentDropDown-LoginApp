package com.sagar.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="COUNTRY_MASTER")
@Data
public class CountryEntity {
	

	@Id
	@SequenceGenerator(name="country",sequenceName="country_id_seq",allocationSize=1)
	@GeneratedValue(generator="country", strategy=GenerationType.SEQUENCE)
	@Column(name="COUNTRY_ID")
	private int countryId;
	
	@Column(name="COUNTRY_CODE")
	private int countryCode;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
}
