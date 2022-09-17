package com.Intuittaxi.com.Intuittaxi.service;

import com.Intuittaxi.com.Intuittaxi.entity.*;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DrivingLicenceDoc;
import com.Intuittaxi.com.Intuittaxi.entity.documents.VehicleInsuranceDoc;
import com.Intuittaxi.com.Intuittaxi.service.utils.DocumentFetcherUtil;
import com.Intuittaxi.com.Intuittaxi.service.utils.FileStorageUtil;
import com.Intuittaxi.com.Intuittaxi.service.utils.UpdateCommonDocInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class DocumentCollectionService {

    @Autowired
    StateUpdaterService stateUpdaterService;
    @Autowired
    FileStorageUtil fileStorageUtil;
    @Autowired
    DocumentFetcherUtil documentFetcherUtil;
    @Autowired
    UpdateCommonDocInfoUtil updateCommonDocInfoUtil;

    /* TODO
    Implement other APIs
    public  String savePanCard(String fileName, MultipartFile multipartFile, Long driverId) {
    }

    public String saveRegisterationCertificate(String fileName, MultipartFile multipartFile, Long driverId) {
    }

    public String saveProfilePicture(String fileName, MultipartFile multipartFile, Long driverId) {
    }*/
    public String saveDrivingLicence(String fileName, MultipartFile multipartFile, Long driverId) throws IOException {
        String fileCode;
        //create OR update document
        Documents document = documentFetcherUtil.fetchDocument(driverId);
        DrivingLicenceDoc docItem = new DrivingLicenceDoc();
        fileCode = updateCommonDocInfoUtil.setCommonDocInfo(docItem,fileName, multipartFile);
        document.setDrivingLicence(docItem);
        documentFetcherUtil.updateDocument(document);

        //call the machine to check and update the states
        stateUpdaterService.updateState(driverId);

        return fileCode;
    }
    public String saveVehicleInsurance(String fileName, MultipartFile multipartFile, Long driverId) throws IOException {
        String fileCode;
        //create OR update document
        Documents document = documentFetcherUtil.fetchDocument(driverId);
        VehicleInsuranceDoc docItem = new VehicleInsuranceDoc();
        fileCode = updateCommonDocInfoUtil.setCommonDocInfo(docItem,fileName, multipartFile);
        document.setVehicleInsurance(docItem);
        documentFetcherUtil.updateDocument(document);

        //call the machine to check and update the states
        stateUpdaterService.updateState(driverId);

        return fileCode;
    }

    public Resource getDrivingLicense(Long driverId) throws IOException {
        Documents document = documentFetcherUtil.fetchDocument(driverId);
        String fileCode = document.getVehicleInsurance().getUrl();
        return fileStorageUtil.fetchFile(fileCode);
    }
    public Resource getVehicleInsurance(Long driverId) throws IOException {
        Documents document = documentFetcherUtil.fetchDocument(driverId);
        String fileCode = document.getVehicleInsurance().getUrl();
        return fileStorageUtil.fetchFile(fileCode);
    }
}
