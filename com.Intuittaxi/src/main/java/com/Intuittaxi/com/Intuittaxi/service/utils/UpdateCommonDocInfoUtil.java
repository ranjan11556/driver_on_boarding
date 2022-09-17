package com.Intuittaxi.com.Intuittaxi.service.utils;

import com.Intuittaxi.com.Intuittaxi.entity.documents.DocItem;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocStatus;
import com.Intuittaxi.com.Intuittaxi.entity.documents.VehicleInsuranceDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class UpdateCommonDocInfoUtil {
    @Autowired
    FileStorageUtil fileStorageUtil;

    public String setCommonDocInfo(DocItem docItem, String fileName, MultipartFile multipartFile) throws IOException {
        String fileCode = fileStorageUtil.uploadFile(fileName, multipartFile);
        docItem.setStatus(DocStatus.VERIFICATION_IN_PROGRESS);
        docItem.setUrl(fileCode);
        return fileCode;
    }
}
