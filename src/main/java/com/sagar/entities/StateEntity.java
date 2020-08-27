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
@Table(name="STATE_MASTER")
@Data
public class StateEntity {
	

	@Id
	@SequenceGenerator(name="state",sequenceName="state_id_seq",allocationSize=1)
	@GeneratedValue(generator="state", strategy=GenerationType.SEQUENCE)
	@Column(name="STATE_ID")
	private int stateId;
	
	@Column(name="COUNTRY_ID")
	private int countryId;
	
	@Column(name="STATE_NAME")
	private String stateName;

	
}
