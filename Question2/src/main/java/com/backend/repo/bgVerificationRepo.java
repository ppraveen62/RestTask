package com.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.backgroundVerificationRequest;
import com.backend.entity.tdShipingRequest;


public interface bgVerificationRepo extends JpaRepository<backgroundVerificationRequest,Long> {
	
}
