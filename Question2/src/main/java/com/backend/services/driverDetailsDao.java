package com.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entity.driverDetails;
import com.backend.repo.driverDetailsRepo;


@Service
public class driverDetailsDao {
	
	@Autowired
	driverDetailsRepo detailsrepo;

	public driverDetails insert(driverDetails data) {
		data.setEnabled(true);
		data.setRole("USER");
		data.setDriverStatus(false);
		return detailsrepo.save(data);
		
	}
	

	public List<driverDetails> getdriver() {
		return detailsrepo.findAll();
	}
	
	public driverDetails getbyDriverId(long id) {
		if (detailsrepo.findById(id).isPresent()) {
			return detailsrepo.findById(id).get();
		}
		return null;
	}

	
	public driverDetails updateStatus(long id) {
		if (detailsrepo.findById(id).isPresent()) {
			driverDetails oldData = detailsrepo.findById(id).get();
			if(oldData.getDriverStatus()) {
				oldData.setDriverStatus(false);
			}else {
				oldData.setDriverStatus(true);
			}
			
			return detailsrepo.save(oldData);
		} else
			return null;
	}



	
}
