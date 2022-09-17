package com.Intuittaxi.com.Intuittaxi.service;

import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import com.Intuittaxi.com.Intuittaxi.entity.ProfileCurrentState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationService {
    public void notify(Driver driver, ProfileCurrentState nextDocStatus) {
        //sent details to notification service
        log.info("driver: " + driver.getFirstName() + "is currently in step : " + nextDocStatus);
    }
}
