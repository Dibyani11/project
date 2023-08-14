package com.aruparking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingHistoryDTO;
import com.aruparking.DTO.ParkingUserDTO;
import com.aruparking.service.ParkingHistoryService;

@RestController
@RequestMapping
public class ParkingHistoryController {

	@Autowired
	ParkingHistoryService parkingHistoryService;

	// http://localhost:8088/user/4
	@GetMapping("/user/{id}")
	public List<ParkingHistoryDTO> getHistoryByUserId(@PathVariable long id) {
		return parkingHistoryService.getHistoryById(id);
	}

	// http://localhost:8088/user/active/4
	@GetMapping("/user/active/{id}")
	public List<ParkingHistoryDTO> getActiveHistory(@PathVariable long id) {
		return parkingHistoryService.getActiveHistoryById(id);
	}
}
