package com.aruparking.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingHistoryDTO;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingUser;
import com.aruparking.repository.ParkingFeeRepository;
import com.aruparking.repository.ParkingOrderRepository;
import com.aruparking.repository.ParkingSlotsRepository;
import com.aruparking.repository.ParkingUserRepository;
import com.aruparking.repository.ParkingZonesRepository;
import com.aruparking.service.ParkingHistoryService;

@Service
public class ParkingHistoryServiceImpl implements ParkingHistoryService {

	@Autowired
	ParkingUserRepository parkingUserRepo;

	@Autowired
	ParkingSlotsRepository parkingSlotsRepo;

	@Autowired
	ParkingZonesRepository parkingZonesRepo;

	@Autowired
	ParkingFeeRepository parkingFeeRepo;

	@Autowired
	ParkingOrderRepository parkingOrderRepo;

	@Override
	public List<ParkingHistoryDTO> getHistoryById(long id) {// past user history

		ParkingUser userId = parkingUserRepo.findById(id);

		Date date = new Date();
		// System.out.println(date);

		if (userId != null) {

			List<ParkingOrder> order = parkingOrderRepo.findAllByParkingUserId(id);
			List<ParkingHistoryDTO> userDTO = new ArrayList<>();

			for (ParkingOrder parkingOrder : order) {
				ParkingHistoryDTO dto = new ParkingHistoryDTO();

				int endtime = date.compareTo(parkingOrder.getParkingEndTime());

				if (endtime > 0) {
					dto.setUserId(parkingOrder.getParkingUser().getId());
					dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					dto.setSlotId(parkingOrder.getParkingSlots().getId());
					dto.setOrderId(parkingOrder.getId());
					dto.setFeeId(parkingOrder.getParkingFee().getId());
					dto.setAmount(parkingOrder.getAmount());
					dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					dto.setVehicleNo(parkingOrder.getVehicleNo());
					dto.setPayee("Aru Parking");
					dto.setTransactionId(parkingOrder.getTransactionId());
					dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());

					userDTO.add(dto);
				}
			}
			return userDTO;
		} else {
			throw new RuntimeException("user id is not present.");
		}
	}

	@Override
	public List<ParkingHistoryDTO> getActiveHistoryById(long id) {

		ParkingUser userId = parkingUserRepo.findById(id);

		Date date = new Date();

		if (userId != null) {

			List<ParkingOrder> order = parkingOrderRepo.findAllByParkingUserId(id);
			List<ParkingHistoryDTO> userDTO = new ArrayList();

			for (ParkingOrder parkingOrder : order) {
				ParkingHistoryDTO dto = new ParkingHistoryDTO();

				int endtime = date.compareTo(parkingOrder.getParkingEndTime());

				if (endtime < 0) {
					dto.setUserId(parkingOrder.getParkingUser().getId());
					dto.setZoneId(parkingOrder.getParkingSlots().getParkingZones().getId());
					dto.setSlotId(parkingOrder.getParkingSlots().getId());
					dto.setOrderId(parkingOrder.getId());
					dto.setFeeId(parkingOrder.getParkingFee().getId());
					dto.setAmount(parkingOrder.getAmount());
					dto.setParkingStartTime(parkingOrder.getParkingStartTime());
					dto.setParkingEndTime(parkingOrder.getParkingEndTime());
					dto.setVehicleNo(parkingOrder.getVehicleNo());
					dto.setPayee("Aru Parking");
					dto.setTransactionId(parkingOrder.getTransactionId());
					dto.setSlotName(parkingOrder.getParkingSlots().getSlotName());

					userDTO.add(dto);
				}
			}
			return userDTO;
		}
		return null;
	}
}