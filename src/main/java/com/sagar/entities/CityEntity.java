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
@Table(name="CITY_MASTER")
@Data
public class CityEntity {
	

	@Id
	@SequenceGenerator(name="city",sequenceName="city_id_seq",allocationSize=1)
	@GeneratedValue(generator="city", strategy=GenerationType.SEQUENCE)
	@Column(name="CITY_ID")
	private int cityId;
	
	@Column(name="STATE_ID")
	private int stateId;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
}
