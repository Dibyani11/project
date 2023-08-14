package com.aruparking.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingOrderDTO;
import com.aruparking.model.ParkingFee;
import com.aruparking.model.ParkingOrder;
import com.aruparking.model.ParkingSlots;
import com.aruparking.model.ParkingUser;
import com.aruparking.repository.ParkingFeeRepository;
import com.aruparking.repository.ParkingOrderRepository;
import com.aruparking.repository.ParkingSlotsRepository;
import com.aruparking.repository.ParkingUserRepository;
import com.aruparking.service.ParkingOrderService;

@Service
public class ParkingOrderServiceImpl implements ParkingOrderService {

	@Autowired
	ParkingOrderRepository parkingOrderRepo;
	@Autowired
	ParkingUserRepository parkingUserRepo;
	@Autowired
	ParkingSlotsRepository parkingSlotsRepo;
	@Autowired
	ParkingFeeRepository parkingFeeRepo;

	@Override
	public ParkingOrderDTO placingOrder(ParkingOrderDTO parkingOrderDTO) {

//		ParkingOrder order = new ParkingOrder();
		ParkingUser user = parkingUserRepo.findById(parkingOrderDTO.getUserId());

		if (user != null) {
			ParkingSlots pslot = parkingSlotsRepo.findById(parkingOrderDTO.getSlotsId());
			if (pslot != null) {
				System.out.println("slot is there");
				ParkingFee pfee = parkingFeeRepo.findById(parkingOrderDTO.getFeeId());
				// System.out.println("slot");
				if (pfee != null) {
					ParkingOrder order = new ParkingOrder();
					BigDecimal tamnt = pfee.getAmmount();
					BigDecimal balance = user.getBalance();

					int deduction = balance.compareTo(tamnt);

					if (deduction >= 0) {
						BigDecimal currentbalance = balance.subtract(tamnt);
						user.setBalance(currentbalance);
						parkingUserRepo.save(user);

						Date dt = new Date();
						Date time = DateUtils.addHours(dt, Integer.parseInt(pfee.getTiming()));

						order.setDescription("Payment Sucessful");
						order.setTransactionId(UUID.randomUUID().toString());
						order.setParkingUser(user);
						order.setParkingSlots(pslot);
						order.setParkingFee(pfee);
						order.setVehicleNo(parkingOrderDTO.getVehicleNo());
						order.setContactNo(parkingOrderDTO.getContactNo());
						order.setAmount(pfee.getAmmount());
						order.setParkingEndTime(time);

						ParkingOrder neworder = parkingOrderRepo.save(order);

						ParkingOrderDTO dto = new ParkingOrderDTO();
						dto.setDescription("Payment Sucessful");
						dto.setAmount(neworder.getAmount());
						dto.setId(neworder.getId());
						dto.setTransactionId(neworder.getTransactionId());

						return dto;
					} else {
						throw new RuntimeException("Balance is lessthan amount to be deducted.");
					}
				} else {
					throw new RuntimeException("Fee Id is not present.");
				}
			} else {
				throw new RuntimeException(" Slot is not Present.");
			}
		} else {
			throw new RuntimeException("User Id is not present");
		}
	}
}
