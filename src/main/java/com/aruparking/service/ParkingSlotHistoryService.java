package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingSlotHistoryDTO;

public interface ParkingSlotHistoryService {

	public List<ParkingSlotHistoryDTO> getSlotHistoryById(long id);

	public List<ParkingSlotHistoryDTO> getActiveSlotHistory(long id);
}
