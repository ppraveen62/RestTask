package com.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.Users;
import com.backend.repo.UsersRepo;


@Service
public class UsersDao {
	
	@Autowired
	UsersRepo userrepo;

	public Users insert(Users u) {
		u.setEnabled(true);
		u.setRole("USER");
		return userrepo.save(u);
		
	}
	

	public List<Users> getusers() {
		return userrepo.findAll();
	}
	
	public Users getUserByEmailAddress(String emailAddress) {
		if (userrepo.findById(emailAddress).isPresent()) {
			return userrepo.findById(emailAddress).get();
		}
		return null;
	}

	
	public Users updateUser(Users newData,String emailAddress) {
		if (userrepo.findById(emailAddress).isPresent()) {
			Users oldData = userrepo.findById(emailAddress).get();
			oldData.setFirstName(newData.getFirstName());
			oldData.setLastName(newData.getLastName());
			oldData.setPhoneNumber(newData.getPhoneNumber());
			return userrepo.save(oldData);
		} else
			return null;
	}



	
}
