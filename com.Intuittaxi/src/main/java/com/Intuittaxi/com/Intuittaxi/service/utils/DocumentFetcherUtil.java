package com.Intuittaxi.com.Intuittaxi.service.utils;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import com.Intuittaxi.com.Intuittaxi.repository.DocumentsRepository;
import com.Intuittaxi.com.Intuittaxi.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentFetcherUtil {
    @Autowired
    DocumentsRepository documentRepository;
    @Autowired
    DriverRepository driverRepository;
    public boolean updateDocument(Documents document){
        documentRepository.save(document);
        return true;  //need exception handling
    }

    public Documents fetchDocument(Long driverID) {
        Driver driver  = driverRepository.findById(driverID).get();
        Documents document = driver.getDocument();
        if(document == null) {
            document = new Documents();
            driver.setDocument(document);
        }
        return document;
    }
}
