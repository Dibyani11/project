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

import com.aruparking.DTO.ParkingSlotsDTO;
import com.aruparking.DTO.ParkingZonesDTO;
import com.aruparking.service.ParkingSlotsService;

@RestController
@RequestMapping("/vendor/slot")
public class ParkingSlotsController {

	@Autowired
	private ParkingSlotsService parkingSlotsService;

	// http://localhost:8088/vendor/slot
	@PostMapping
	public Object crateParkingSlots(@RequestBody ParkingSlotsDTO parkingSlotsDTO) {
		return parkingSlotsService.addParkingSlots(parkingSlotsDTO);
	}

	// http://localhost:8088/vendor/slot/getslots
	@GetMapping("/getslots")
	public List<ParkingSlotsDTO> getAllSlots() {
		return parkingSlotsService.getAllSlots();
	}

	// http://localhost:8088/vendor/slot/2
	@GetMapping("/{id}")
	public ParkingSlotsDTO getSlotById(@PathVariable long id) {
		return parkingSlotsService.getSlotById(id);
	}

	// http://localhost:8088/vendor/slot/updatedslot
	@PutMapping("/updatedslot")
	public ParkingSlotsDTO updateSlots(@RequestBody ParkingSlotsDTO parkingSlotsDTO) {
		return parkingSlotsService.updateSlots(parkingSlotsDTO);
	}
}