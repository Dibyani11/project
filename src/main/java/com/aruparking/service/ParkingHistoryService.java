package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingHistoryDTO;
import com.aruparking.model.ParkingUser;

public interface ParkingHistoryService {

	public List<ParkingHistoryDTO> getHistoryById(long id);
	
	public List<ParkingHistoryDTO> getActiveHistoryById(long id);
	
}
