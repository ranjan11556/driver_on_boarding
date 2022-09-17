package com.Intuittaxi.com.Intuittaxi.controller;

import com.Intuittaxi.com.Intuittaxi.dto.LoginDTO;
import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import com.Intuittaxi.com.Intuittaxi.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class DriverController {

    @Autowired
    ProfileService profileService;

    @PostMapping("/login")
    public Driver doLogin(@RequestBody LoginDTO loginDTO) {
        return profileService.login(loginDTO);
    }

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Driver doSignup(@Valid @RequestBody Driver driver) {
        return profileService.signup(driver);
    }

    @PostMapping("/logout/{driverId}")
    // TODO: check return and method type
    public void doLogout(@PathVariable("driverId") Long driverId) {
        profileService.logout(driverId);
    }

    @GetMapping("/applicationStatus/{driverId}")
    public String getProfileStatus(@PathVariable("driverId") Long driverId) {
        return profileService.profileStatus(driverId);
    }
    @PutMapping("/enableDrive/{driverId}")
    public String markReadyToDrive(@PathVariable("driverId") Long driverId) {
        return profileService.markReadyToDrive(driverId);
    }
}