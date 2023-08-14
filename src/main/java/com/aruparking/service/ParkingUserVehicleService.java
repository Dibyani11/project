package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingUserVehicleDTO;

public interface ParkingUserVehicleService {

	public ParkingUserVehicleDTO addVehicle(ParkingUserVehicleDTO parkingUserVehicleDTO);
	
	public ParkingUserVehicleDTO updateVehicle(ParkingUserVehicleDTO parkingUserVehicleDTO);
	
	public Object deleteVehicleById(long id);
	
	public List<ParkingUserVehicleDTO> getSavedVehicleById(long id);
	
	public List<ParkingUserVehicleDTO> getFavoriteUserVehicle(long id);
	
	public List<ParkingUserVehicleDTO>  getDefaultUserVehicle(long id);
}
