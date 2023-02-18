package com.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class driverDocuments{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long driverId;
	private String docName;
	private String docType;
	@Lob
	private byte[] data;
	
}

