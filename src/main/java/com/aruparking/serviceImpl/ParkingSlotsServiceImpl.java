package com.aruparking.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingSlotsDTO;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingZones;
import com.aruparking.repository.ParkingSlotsRepository;
import com.aruparking.repository.ParkingZonesRepository;
import com.aruparking.service.ParkingSlotsService;

@Service
public class ParkingSlotsServiceImpl implements ParkingSlotsService {
	@Autowired
	ParkingSlotsRepository parkingSlotsRepo;
	@Autowired
	ParkingZonesRepository parkingZonesRepo;

//	public ParkingSlotsServiceImpl(ParkingSlotsRepository parkingSlotsRepo, ParkingZonesRepository parkingZonesRepo) {
//		this.parkingSlotsRepo = parkingSlotsRepo;
//		this.parkingZonesRepo = parkingZonesRepo;
//	}

	@Override
	public Object addParkingSlots(ParkingSlotsDTO parkingSlotsDto) {
		ParkingSlots ps = new ParkingSlots();
		ParkingZones parkingZones = parkingZonesRepo.findById(parkingSlotsDto.getZoneId());

		if (parkingZones != null) {
			ParkingSlots parkingSlot = parkingSlotsRepo.findBySlotNameAndParkingZonesId(parkingSlotsDto.getSlotName(),parkingSlotsDto.getZoneId());
			if (parkingSlot == null) {
				ps.setSlotName(parkingSlotsDto.getSlotName());
				ps.setStatus(1);
				ps.setParkingZones(parkingZones);
				ParkingSlots savedSlot = parkingSlotsRepo.save(ps);

				ParkingSlotsDTO dto = new ParkingSlotsDTO();
				dto.setId(savedSlot.getId());
				dto.setSlotName(savedSlot.getSlotName());
				dto.setStatus(savedSlot.getStatus());
				dto.setCreatedOn(savedSlot.getCreatedOn());
				dto.setUpdatedOn(savedSlot.getUpdatedOn());
				dto.setZoneId(savedSlot.getParkingZones().getId());
				return dto;
			} else {
				Map<String, String> map = new HashMap<String, String>();
				map.put("ErrorMessage", "Parking slot with the same name already exists.");
				return map;
			}
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("ErrorMessage", "Parking zone is not there.");
			return map;
		}
	}

	@Override
	public List<ParkingSlotsDTO> getAllSlots() {

		List<ParkingSlots> parkingSlots = parkingSlotsRepo.findAll();
		List<ParkingSlotsDTO> dto = new ArrayList();

		for (ParkingSlots allSlots : parkingSlots) {
			ParkingSlotsDTO pdto = new ParkingSlotsDTO();
			pdto.setId(allSlots.getId());
			pdto.setSlotName(allSlots.getSlotName());
			pdto.setStatus(allSlots.getStatus());
			pdto.setCreatedOn(allSlots.getCreatedOn());
			pdto.setUpdatedOn(allSlots.getUpdatedOn());
			pdto.setZoneId(allSlots.getParkingZones().getId());

			dto.add(pdto);
		}
		return dto;
	}

	@Override
	public ParkingSlotsDTO getSlotById(long id) {
		ParkingSlots slot = parkingSlotsRepo.findById(id);
		ParkingSlotsDTO sdto = new ParkingSlotsDTO();

		sdto.setId(slot.getId());
		sdto.setSlotName(slot.getSlotName());
		sdto.setStatus(slot.getStatus());
		sdto.setCreatedOn(slot.getCreatedOn());
		sdto.setUpdatedOn(slot.getUpdatedOn());
		sdto.setZoneId(slot.getParkingZones().getId());
		return sdto;
	}

	@Override
	public ParkingSlotsDTO updateSlots(ParkingSlotsDTO parkingSlotsDTO) {

		ParkingSlots pslot = parkingSlotsRepo.findById(parkingSlotsDTO.getId());

		if (pslot != null) {
			ParkingSlots parkingSlot = parkingSlotsRepo.findBySlotNameAndParkingZonesId(parkingSlotsDTO.getSlotName(),parkingSlotsDTO.getZoneId());

			if (parkingSlot == null) {
				pslot.setSlotName(parkingSlotsDTO.getSlotName());
				pslot.setStatus(parkingSlotsDTO.getStatus());

				ParkingSlots updatedSlot = parkingSlotsRepo.save(pslot);
				return update(updatedSlot);
			} else {
				throw new RuntimeException("Slot name is already present.");
			}
		} else {
			throw new RuntimeException("ID IS NOT PRESENT");
//			Map<String, String> map = new HashMap<String, String>();
//            map.put("ErrorMessage", "Id is Not Present.");
//            return map;
		}
	}

	public ParkingSlotsDTO update(ParkingSlots updatedSlot) {
		ParkingSlotsDTO dto = new ParkingSlotsDTO();
		dto.setId(updatedSlot.getId());
		dto.setSlotName(updatedSlot.getSlotName());
		dto.setStatus(updatedSlot.getStatus());
		dto.setCreatedOn(updatedSlot.getCreatedOn());
		dto.setUpdatedOn(updatedSlot.getUpdatedOn());
		dto.setZoneId(updatedSlot.getParkingZones().getId());
		return dto;
	}
}
