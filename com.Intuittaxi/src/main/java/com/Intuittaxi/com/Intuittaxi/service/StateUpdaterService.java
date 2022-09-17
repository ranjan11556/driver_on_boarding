package com.Intuittaxi.com.Intuittaxi.service;

import com.Intuittaxi.com.Intuittaxi.entity.Documents;
import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import com.Intuittaxi.com.Intuittaxi.entity.documents.DocStatus;
import com.Intuittaxi.com.Intuittaxi.repository.DriverRepository;
import com.Intuittaxi.com.Intuittaxi.service.StateMachine.*;
import com.Intuittaxi.com.Intuittaxi.service.utils.DocumentFetcherUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
@Slf4j
@Service
public class StateUpdaterService {
    @Autowired
    private DocumentCollectorMachine documentCollectorMachine;
    @Autowired
    private BackGroundVerificationMachine backGroundVerificationMachine;
    @Autowired
    private DeviceShipmentMachine deviceShipmentMachine;
    @Autowired
    private TraningExecuterMachine traningExecuterMachine;
    @Autowired
    DriverRepository driverRepository;
    @Autowired
    NotificationService notificationService;
    @Autowired
    DocumentFetcherUtil documentFetcherUtil;
    public StateUpdaterService(){

    }
    @PostConstruct
    void init(){
        documentCollectorMachine.setNextState(backGroundVerificationMachine);
        backGroundVerificationMachine.setNextState(deviceShipmentMachine);
        deviceShipmentMachine.setNextState(traningExecuterMachine);
    }

    void updateState(Long driverId){
        Driver driver  = driverRepository.findById(driverId).get();
        Documents document = driver.getDocument();
        ProfileCurrentState nextDocStatus = documentCollectorMachine.processState(document);
        driver.setProfileCurrentState(nextDocStatus);
        driverRepository.save(driver);
        //notify drivers
        notificationService.notify(driver, nextDocStatus);
    }
}
