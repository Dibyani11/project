package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingFeeDTO;

public interface ParkingFeeService {
	
   public ParkingFeeDTO addParkingFee(ParkingFeeDTO parkingFeeDTO);
   
   public List<ParkingFeeDTO> getAllFees();
   
}
