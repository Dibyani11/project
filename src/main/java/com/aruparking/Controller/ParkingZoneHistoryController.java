package com.aruparking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingZoneHistoryDTO;
import com.aruparking.service.ParkingZoneHistoryService;

@RestController
@RequestMapping
public class ParkingZoneHistoryController {

	@Autowired
	ParkingZoneHistoryService parkingZoneHistoryService;

	// localhost:8088/zone/2
	@GetMapping("/zone/{id}")
	public List<ParkingZoneHistoryDTO> parkingZoneHistory(@PathVariable long id) {
		return parkingZoneHistoryService.getZoneHistoryById(id);
	}

	// localhost:8088/zone/active/1
	@GetMapping("/zone/active/{id}")
	public List<ParkingZoneHistoryDTO> parkingActiveZoneHistory(@PathVariable long id) {
		return parkingZoneHistoryService.getActiveZoneHistory(id);
	}
}
