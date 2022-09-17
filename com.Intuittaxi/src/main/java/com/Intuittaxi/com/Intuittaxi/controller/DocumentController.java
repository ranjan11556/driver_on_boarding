package com.Intuittaxi.com.Intuittaxi.controller;

import com.Intuittaxi.com.Intuittaxi.dto.FileUploadResponse;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocType;
import com.Intuittaxi.com.Intuittaxi.service.DocumentCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class DocumentController {
    @Autowired
    private DocumentCollectionService documentCollectionService;

    @PostMapping("{driverId}/upload/{docType}")
    public ResponseEntity<FileUploadResponse> uploadDocument(@PathVariable("driverId") Long driverId,
                               @PathVariable("docType") String documentType,
                               @RequestParam("file") MultipartFile multipartFile)
            throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();

        String filecode = saveFile(fileName, multipartFile, driverId, documentType);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String saveFile(String fileName, MultipartFile multipartFile, Long driverId, String documentType) throws IOException {
        DocType docType = DocType.valueOf(documentType);
        String filecode = null;
        switch(docType){
            case DL:
                filecode = documentCollectionService.saveDrivingLicence(fileName, multipartFile, driverId);
                break;
            case VEHICLE_PERMIT:
                filecode = documentCollectionService.saveVehicleInsurance(fileName, multipartFile, driverId);
                break;
             /* TODO add APIs OR uncomment below code as per need Once services are implemented.
            case PAN:
                filecode = documentCollectionService.savePanCard(fileName, multipartFile, driverId);
                break;
            case RC:
                filecode = documentCollectionService.saveRegisterationCertificate(fileName, multipartFile, driverId);
                break;
            case PROFILE_PIC:
                filecode = documentCollectionService.saveProfilePicture(fileName, multipartFile, driverId);
                break;
        */
            default:
                break;
        }
        return filecode;
    }

    @GetMapping("{driverId}/upload/{documentType}")
    public ResponseEntity<Object> getDocument(@PathVariable("driverId") Long driverId, @PathVariable("documentType") String documentType)
            throws IOException {

        Resource resource = resource = getFile(driverId, documentType);

        if (resource == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(resource);
    }

    private Resource getFile(Long driverId, String documentType) throws IOException {
        Resource resource = null;
        DocType docType = DocType.valueOf(documentType);
        String filecode = null;
        switch(docType){

            case DL:
                resource = documentCollectionService.getDrivingLicense(driverId);
                break;
            case VEHICLE_PERMIT:
                resource = documentCollectionService.getVehicleInsurance(driverId);
                break;
                /* TODO add APIs OR uncomment below code as per need Once services are implemented.
            case PAN:
                filecode = documentCollectionService.getPanCard(fileName, multipartFile, driverId);
                break;
            case RC:
                filecode = documentCollectionService.getRegisterationCertificate(fileName, multipartFile, driverId);
                break;
            case PROFILE_PIC:
                filecode = documentCollectionService.getProfilePicture(fileName, multipartFile, driverId);
                break;
            */

            default:
                break;
        }
        return resource;
    }

}
