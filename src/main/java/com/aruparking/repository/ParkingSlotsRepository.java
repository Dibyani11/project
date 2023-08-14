package com.aruparking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;

@Repository
public interface ParkingSlotsRepository extends JpaRepository<ParkingSlots, Long> {

	ParkingSlots findBySlotNameAndParkingZonesId(String slotName, long zoneId);
	
	ParkingSlots findById(long id);

}
