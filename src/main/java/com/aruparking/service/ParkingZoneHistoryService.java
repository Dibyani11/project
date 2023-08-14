package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingZoneHistoryDTO;

public interface ParkingZoneHistoryService {

	public List<ParkingZoneHistoryDTO> getZoneHistoryById(long id);

	public List<ParkingZoneHistoryDTO> getActiveZoneHistory(long id);
}
