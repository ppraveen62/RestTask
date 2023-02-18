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
public class backgroundVerificationRequest{
	@Id
	private long driverId;
	private String drivingLicense;
	private String vechileRegistration;
	private Boolean verifyStatus;
}

