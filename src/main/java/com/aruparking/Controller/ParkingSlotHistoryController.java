package com.aruparking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingSlotHistoryDTO;
import com.aruparking.DTO.ParkingZoneHistoryDTO;
import com.aruparking.service.ParkingSlotHistoryService;

@RestController
@RequestMapping
public class ParkingSlotHistoryController {

	@Autowired
	ParkingSlotHistoryService parkingSlotHistoryService;

	// localhost:8088/slot/2
	@GetMapping("/slot/{id}")
	public List<ParkingSlotHistoryDTO> parkingSlotHistory(@PathVariable long id) {
		return parkingSlotHistoryService.getSlotHistoryById(id);
	}

	// localhost:8088/slot/active/1
	@GetMapping("/slot/active/{id}")
	public List<ParkingSlotHistoryDTO> parkingSlotActiveHistory(@PathVariable long id) {
		return parkingSlotHistoryService.getActiveSlotHistory(id);
	}

}
