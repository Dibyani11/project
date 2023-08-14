package com.aruparking.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingHistoryDTO;
import com.aruparking.DTO.ParkingZoneHistoryDTO;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingUser;
import com.aruparking.model.ParkingZones;
import com.aruparking.repository.ParkingFeeRepository;
import com.aruparking.repository.ParkingOrderRepository;
import com.aruparking.repository.ParkingSlotsRepository;
import com.aruparking.repository.ParkingUserRepository;
import com.aruparking.repository.ParkingZonesRepository;
import com.aruparking.service.ParkingZoneHistoryService;

@Service
public class ParkingZoneHistoryServiceImpl implements ParkingZoneHistoryService {

	@Autowired
	ParkingUserRepository parkingUserRepo;

	@Autowired
	ParkingOrderRepository parkingOrderRepo;

	@Autowired
	ParkingFeeRepository parkingFeeRepo;

	@Autowired
	ParkingZonesRepository parkingZoneRepo;

	@Autowired
	ParkingSlotsRepository parkingSlotRepo;

	@Override
	public List<ParkingZoneHistoryDTO> getZoneHistoryById(long id) {

		ParkingZones zone = parkingZoneRepo.findById(id);
		Date dt = new Date();

		if (zone != null) {

			List<ParkingOrder> porder = parkingOrderRepo.findAllByParkingSlotsParkingZonesId(id);
			List<ParkingZoneHistoryDTO> zdto = new ArrayList();

			for (ParkingOrder parkingOrder : porder) {
				ParkingZoneHistoryDTO dto = new ParkingZoneHistoryDTO();

				int time = dt.compareTo(parkingOrder.getParkingEndTime());

				if (time > 0) {
					dto.setUserId(parkingOrder.getParkingUser().getId());
					dto.setSlotId(parkingOrder.getParkingSlots().getId());
					dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					dto.setOrderId(parkingOrder.getId());
					dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					dto.setVehicleNo(parkingOrder.getVehicleNo());
					dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());
					dto.setFeeId(parkingOrder.getParkingFee().getId());
					dto.setAmount(parkingOrder.getAmount());
					dto.setPayee("Aru Parking");
					dto.setTransactionId(parkingOrder.getTransactionId());

					zdto.add(dto);
				}
			}
			return zdto;
		}
		return null;
	}

	@Override
	public List<ParkingZoneHistoryDTO> getActiveZoneHistory(long id) {
		ParkingZones zone = parkingZoneRepo.findById(id);
		Date dt = new Date();

		if (zone != null) {

			List<ParkingOrder> porder = parkingOrderRepo.findAllByParkingSlotsParkingZonesId(id);
			List<ParkingZoneHistoryDTO> zdto = new ArrayList();

			for (ParkingOrder parkingOrder : porder) {
				ParkingZoneHistoryDTO dto = new ParkingZoneHistoryDTO();

				int time = dt.compareTo(parkingOrder.getParkingEndTime());

				if (time < 0) {
					dto.setUserId(parkingOrder.getParkingUser().getId());
					dto.setSlotId(parkingOrder.getParkingSlots().getId());
					dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					dto.setOrderId(parkingOrder.getId());
					dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					dto.setVehicleNo(parkingOrder.getVehicleNo());
					dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());
					dto.setFeeId(parkingOrder.getParkingFee().getId());
					dto.setAmount(parkingOrder.getAmount());
					dto.setPayee("Aru Parking");
					dto.setTransactionId(parkingOrder.getTransactionId());

					zdto.add(dto);
				}
			}
			return zdto;
		}
		return null;
	}

}
