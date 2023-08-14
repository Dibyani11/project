package com.aruparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aruparking.model.ParkingZones;

public interface ParkingZonesRepository extends JpaRepository<ParkingZones, Long> {

	ParkingZones findById(long id);

}

