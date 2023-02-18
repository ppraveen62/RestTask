package com.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entity.driverDocuments;

public interface driverDocumentsRepo extends JpaRepository<driverDocuments,Long> {
	
}
