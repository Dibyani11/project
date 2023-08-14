package com.aruparking.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingUserVehicleDTO;
import com.aruparking.service.ParkingUserVehicleService;

@RestController
@RequestMapping("/account")
public class ParkingUserVehicleController {

	@Autowired
	ParkingUserVehicleService parkingUserVehicleService;
	
	//localhost:8088/account/add
	@PostMapping("/add")
	public ParkingUserVehicleDTO addVehicle(@RequestBody ParkingUserVehicleDTO parkingUserVehicleDTO) {
		return parkingUserVehicleService.addVehicle(parkingUserVehicleDTO);
	}
	
	//localhost:8088/account/update
	@PutMapping("/update")
	public ParkingUserVehicleDTO updateUserVehicle(@RequestBody ParkingUserVehicleDTO parkingUserVehicleDTO) {
		return parkingUserVehicleService.updateVehicle(parkingUserVehicleDTO);
	}
	
	//localhost:8088/account/delete/3
	@DeleteMapping("/delete/{id}")
	public Object deleteUserVehicle(@PathVariable long id) {
		return parkingUserVehicleService.deleteVehicleById(id); 
	}
	
	
	//localhost:8088/account/all/4
	@GetMapping("/all/{id}")
	public List<ParkingUserVehicleDTO> getAllVehicle(@PathVariable long id){
		return parkingUserVehicleService.getSavedVehicleById(id);
	}
	
	//localhost:8088/account/fav/4
	@GetMapping("/fav/{id}")
	public List<ParkingUserVehicleDTO> getFavVehicle(@PathVariable long id){
		return parkingUserVehicleService.getFavoriteUserVehicle(id);
	}
	
	
	//localhost:8088/account/default/6
	@GetMapping("/default/{id}")
	public List<ParkingUserVehicleDTO> getDefaultVehicle(@PathVariable long id){
		return parkingUserVehicleService.getDefaultUserVehicle(id);
	}
}
