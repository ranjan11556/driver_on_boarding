package com.Intuittaxi.com.Intuittaxi.service;

import com.Intuittaxi.com.Intuittaxi.dto.LoginDTO;
import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import com.Intuittaxi.com.Intuittaxi.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    DriverRepository driverRepository;

    public Driver login(LoginDTO loginDTO) {
        return null;
    }

    public Driver signup(Driver driver) {
        return driverRepository.save(driver);
    }

    public void logout(Long driverId) {
    }

    public String markReadyToDrive(Long driverId) {
        Driver driver  = driverRepository.findById(driverId).get();
        ProfileCurrentState profileCurrentState = driver.getProfileCurrentState();
        if(profileCurrentState == ProfileCurrentState.READY_TO_DRIVE){
             return "All ready marked for ready to drive";
        }
        else if(profileCurrentState == ProfileCurrentState.ONBOARDING_COMPLETED){
            driver.setProfileCurrentState(ProfileCurrentState.READY_TO_DRIVE);
            driverRepository.save(driver);
            return "Successfully marked ready for driving";
        }
        else{
            return "Can't drive as on-boarding incomplete";
        }
    }

    public String profileStatus(Long driverId) {
        Driver driver  = driverRepository.findById(driverId).get();
        ProfileCurrentState profileCurrentState = driver.getProfileCurrentState();
        return profileCurrentState.name();
    }
}
