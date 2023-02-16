package com.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.entity.Users;

public interface UsersRepo extends JpaRepository<Users,String> {
	
}
