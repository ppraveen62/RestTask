package com.backend.controler;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.entity.backgroundVerificationRequest;
import com.backend.entity.driverDetails;
import com.backend.repo.bgVerificationRepo;
import com.backend.repo.driverDetailsRepo;
import com.backend.repo.tdShipingRequestRepo;
import com.backend.services.driverDetailsDao;
import com.backend.services.driverDocumentsDao;


import com.backend.entity.driverDocuments;
import com.backend.entity.tdShipingRequest;




@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	private driverDetailsRepo detailsrepo;

	@Autowired
	private driverDetailsDao detailsdao;

	@Autowired
	private driverDocumentsDao documentsdao;

	@Autowired
	private bgVerificationRepo verificationrepo;

	@Autowired
	private tdShipingRequestRepo shippingrepo;

	
	// signup

	@PostMapping("/createAccount")
	public ResponseEntity<Object> driverSignUp(@RequestBody driverDetails userData){
		driverDetails resp = null;
		if(detailsrepo.findById(userData.getDriverId()).isPresent()) {
			return new ResponseEntity<>("Email id already present",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		else {
			resp=detailsdao.insert(userData);

			if(resp!=null) {
				return new ResponseEntity<>(resp,HttpStatus.CREATED);
			}

			else {
				return new ResponseEntity<>("Error Creating Account",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}


	}


  // driving status
	
	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<Object> updateStatus(@PathVariable Long id)

	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		if(username.equals(String.valueOf(id))) {
			driverDetails resp=detailsdao.updateStatus(id);
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



//triggering doc collection

	@PostMapping("/uploadDocument/{id}")
	public ResponseEntity<Object> driverDoc( MultipartFile file , @PathVariable Long id) throws IOException{

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if(username.equals(String.valueOf(id))) {

			driverDocuments resp=documentsdao.upload(file,id);

			if(resp!=null) {
				return new ResponseEntity<>("uploaded sucessfully",HttpStatus.OK);
			}

			else {
				return new ResponseEntity<>("no driver available with that id",HttpStatus.NOT_FOUND);
			}
		}
		else {
			return new ResponseEntity<>("check credentials",HttpStatus.UNAUTHORIZED);
		}

	}

	
	//triggering verification of background


	@PostMapping("/verification/{id}")
	public ResponseEntity<Object> backgroundVerification(@RequestBody backgroundVerificationRequest userData, @PathVariable Long id){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if(username.equals(String.valueOf(id))) {

			backgroundVerificationRequest resp = null;
			if(verificationrepo.findById(id).isPresent()) {
				return new ResponseEntity<>("Email id already present in list",HttpStatus.INTERNAL_SERVER_ERROR);

			}
			else {
				userData.setDriverId(id);
				resp=verificationrepo.save(userData);
				System.out.println(resp);

				if(resp!=null) {
					return new ResponseEntity<>("request has sent",HttpStatus.CREATED);
				}

				else {
					return new ResponseEntity<>("Error request",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		}
		else {
			return new ResponseEntity<>("check credentials",HttpStatus.UNAUTHORIZED);
		}





	}

  //triggering tracking device shipping request

	@PostMapping("/shippingRequest/{id}")
	public ResponseEntity<Object> tarckingdevice(@RequestBody tdShipingRequest userData , @PathVariable Long id){


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		if(username.equals(String.valueOf(id))) {

			tdShipingRequest resp = null;
			if(shippingrepo.findById(id).isPresent()) {
				return new ResponseEntity<>("Email id already present in list",HttpStatus.INTERNAL_SERVER_ERROR);

			}
			else {
				userData.setDriverId(id);
				resp=shippingrepo.save(userData);

				if(resp!=null) {
					return new ResponseEntity<>("Shipping request has sent",HttpStatus.CREATED);
				}

				else {
					return new ResponseEntity<>("Error request",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}

		}
		else {
			return new ResponseEntity<>("check credentials",HttpStatus.UNAUTHORIZED);
		}


	}



}
