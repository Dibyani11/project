package com.aruparking.service;

import java.util.List;

import com.aruparking.DTO.ParkingSlotsDTO;

public interface ParkingSlotsService {

	public Object addParkingSlots(ParkingSlotsDTO parkingSlotsDto);

	public List<ParkingSlotsDTO> getAllSlots();

	public ParkingSlotsDTO getSlotById(long id);

	public ParkingSlotsDTO updateSlots(ParkingSlotsDTO parkingSlotsDTO);

}
