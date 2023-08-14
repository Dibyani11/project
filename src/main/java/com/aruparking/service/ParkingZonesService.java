package com.aruparking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.aruparking.DTO.ParkingZonesDTO;
import com.aruparking.model.ParkingZones;

public interface ParkingZonesService {

	public ParkingZonesDTO createParkingZone(ParkingZonesDTO parkingZonesDto);

	public List<ParkingZonesDTO> getAllZones();

	public ParkingZonesDTO getZoneById(long id);

	public ParkingZonesDTO updateZone(ParkingZonesDTO parkingZonesDTO);

}