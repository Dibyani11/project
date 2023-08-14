package com.aruparking.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingSlotHistoryDTO;
import com.aruparking.DTO.ParkingZoneHistoryDTO;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingSlots;
import com.aruparking.repository.ParkingOrderRepository;
import com.aruparking.repository.ParkingSlotsRepository;
import com.aruparking.service.ParkingSlotHistoryService;

@Service
public class ParkingSlotHistoryServiceImpl implements ParkingSlotHistoryService {

	@Autowired
	ParkingSlotsRepository parkingSlotsRepo;

	@Autowired
	ParkingOrderRepository parkingOrderRepo;

	@Override
	public List<ParkingSlotHistoryDTO> getSlotHistoryById(long id) {

		ParkingSlots slots = parkingSlotsRepo.findById(id);
		if (slots != null) {

			List<ParkingOrder> order = parkingOrderRepo.findAllByParkingSlotsId(id);
			List<ParkingSlotHistoryDTO> dto = new ArrayList();

			Date date = new Date();

			for (ParkingOrder parkingOrder : order) {
				ParkingSlotHistoryDTO Dto = new ParkingSlotHistoryDTO();

				int time = date.compareTo(parkingOrder.getParkingEndTime());

				if (time > 0) {
					Dto.setUserId(parkingOrder.getParkingUser().getId());
					Dto.setSlotId(parkingOrder.getParkingSlots().getId());
					Dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					Dto.setOrderId(parkingOrder.getId());
					Dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					Dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					Dto.setVehicleNo(parkingOrder.getVehicleNo());
					Dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());
					Dto.setFeeId(parkingOrder.getParkingFee().getId());
					Dto.setAmount(parkingOrder.getAmount());
					Dto.setPayee("Aru Parking");
					Dto.setTransactionId(parkingOrder.getTransactionId());

					dto.add(Dto);
				}
			}
			return dto;
		}
		return null;
	}

	@Override
	public List<ParkingSlotHistoryDTO> getActiveSlotHistory(long id) {

		ParkingSlots slots = parkingSlotsRepo.findById(id);
		if (slots != null) {

			List<ParkingOrder> order = parkingOrderRepo.findAllByParkingSlotsId(id);
			List<ParkingSlotHistoryDTO> dto = new ArrayList();

			Date date = new Date();

			for (ParkingOrder parkingOrder : order) {
				ParkingSlotHistoryDTO Dto = new ParkingSlotHistoryDTO();

				int time = date.compareTo(parkingOrder.getParkingEndTime());

				if (time < 0) {
					Dto.setUserId(parkingOrder.getParkingUser().getId());
					Dto.setSlotId(parkingOrder.getParkingSlots().getId());
					Dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					Dto.setOrderId(parkingOrder.getId());
					Dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					Dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					Dto.setVehicleNo(parkingOrder.getVehicleNo());
					Dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());
					Dto.setFeeId(parkingOrder.getParkingFee().getId());
					Dto.setAmount(parkingOrder.getAmount());
					Dto.setPayee("Aru Parking");
					Dto.setTransactionId(parkingOrder.getTransactionId());

					dto.add(Dto);
				}
			}
			return dto;
		}
		return null;
	}

}
