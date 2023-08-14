package com.aruparking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingZonesDTO;
import com.aruparking.model.ParkingZones;
import com.aruparking.service.ParkingZonesService;

@RestController
@RequestMapping("/vendor/pzone")
public class ParkingZonesController {

	@Autowired
	private ParkingZonesService parkingZonesService;

	// http:localhost:8088/vendor/pzone
	@PostMapping
	public ResponseEntity<ParkingZonesDTO> createParkingZone(@RequestBody ParkingZonesDTO parkingZonesDTO) {
		ParkingZonesDTO createdPZone = parkingZonesService.createParkingZone(parkingZonesDTO);
		return new ResponseEntity<>(createdPZone, HttpStatus.CREATED);
	}

	// http:localhost:8088/vendor/pzone/allZones
	@GetMapping("/allZones")
	public List<ParkingZonesDTO> getAllZones() {
		return parkingZonesService.getAllZones();
	}

	// http:localhost:8088/vendor/pzone/1
	@GetMapping("/{id}")
	public ParkingZonesDTO getById(@PathVariable Long id) {
		return parkingZonesService.getZoneById(id);
	}

	// http:localhost:8088/vendor/pzone/updated
	@PutMapping("/updated")
	public ParkingZonesDTO updatedParkingZones(@RequestBody ParkingZonesDTO parkingZonesDTO) {
		return parkingZonesService.updateZone(parkingZonesDTO);
	}
}
