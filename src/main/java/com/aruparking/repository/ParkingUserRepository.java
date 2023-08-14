package com.aruparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aruparking.model.ParkingUser;

public interface ParkingUserRepository extends JpaRepository<ParkingUser, Long> {
	
 ParkingUser findById(long id);
 
 
}
