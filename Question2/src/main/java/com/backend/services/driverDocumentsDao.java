package com.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.backend.entity.driverDocuments;
import com.backend.repo.driverDocumentsRepo;






@Service
public class driverDocumentsDao {
	
	@Autowired
	driverDocumentsRepo documentsrepo;

	 public driverDocuments upload(MultipartFile file, long id) throws IOException {
		    
		 driverDocuments doc = new driverDocuments();
		 doc.setDocName(file.getOriginalFilename());
		 doc.setDocType(file.getContentType());
		 doc.setData(file.getBytes());
		 doc.setDriverId(id);
		    return documentsrepo.save(doc);
		  }


	
}
