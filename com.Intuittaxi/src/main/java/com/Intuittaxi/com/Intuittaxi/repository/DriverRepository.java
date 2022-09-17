package com.Intuittaxi.com.Intuittaxi.repository;

import com.Intuittaxi.com.Intuittaxi.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
    Driver findByFirstName(String firstName);
}
