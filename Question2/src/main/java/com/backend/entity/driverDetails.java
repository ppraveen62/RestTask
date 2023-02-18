package com.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class driverDetails{
	@Id
	@GeneratedValue
	private long driverId;
	private String name;
	private String emailAddress;
	private String phoneNumber;
	private String drivingLicense;
	private String vechileRegistration;
	private String password;
	
	private Boolean enabled;
	private String role;
	
	private Boolean driverStatus;
	
}

