package com.backend.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.backend.entity.Users;
import com.backend.repo.UsersRepo;
import com.backend.services.UsersDao;





@RestController
@RequestMapping("/api/user")
public class UsersController {

	@Autowired
	UsersRepo userrepo;

	@Autowired
	private UsersDao udao;


	@PostMapping("/create")
	public ResponseEntity<Object> userSignUp(@RequestBody Users userData){
		Users resp = null;
		if(userrepo.findById(userData.getEmailAddress()).isPresent()) {
			return new ResponseEntity<>("Email id already present",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		else {
			resp=udao.insert(userData);

			if(resp!=null) {
				return new ResponseEntity<>(resp,HttpStatus.CREATED);
			}

			else {
				return new ResponseEntity<>("Error Creating Account",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


	}


	@GetMapping("/get/{emailAddress}")
	public ResponseEntity<Object> getById(@PathVariable String emailAddress){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		String username = auth.getName();
		if(username.equals(emailAddress)) {
			Users resp=udao.getUserByEmailAddress(emailAddress);

			if(resp!=null) {
				return new ResponseEntity<>(resp,HttpStatus.OK);
			}

			else {
				return new ResponseEntity<>("no user available",HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<>("check credentials",HttpStatus.UNAUTHORIZED);
		}

	}

	@PutMapping("/update/{emailAddress}")
	public ResponseEntity<Object> updateUser(@RequestBody Users userData,@PathVariable String emailAddress)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		String username = auth.getName();
		if(username.equals(emailAddress)) {
			Users resp=udao.updateUser(userData,emailAddress);
			if(resp!=null) {
				return new ResponseEntity<>(resp,HttpStatus.OK);
			}

			else {
				return new ResponseEntity<>("no user available to update",HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<>("check credentials",HttpStatus.UNAUTHORIZED);
		}

	}





}
