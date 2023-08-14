package com.aruparking.serviceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingFeeDTO;
import com.aruparking.model.ParkingFee;
import com.aruparking.repository.ParkingFeeRepository;
import com.aruparking.service.ParkingFeeService;

@Service
public class ParkingFeeServiceImpl implements ParkingFeeService {

	@Autowired
	ParkingFeeRepository feeRepo;

	@Override
	public ParkingFeeDTO addParkingFee(ParkingFeeDTO parkingFeeDTO) {
		ParkingFee pf = new ParkingFee();

		BigDecimal bigDecimal1 = new BigDecimal(parkingFeeDTO.getTiming());
		//System.out.println(parkingFeeDTO.getTiming());
		BigDecimal bigDecimal = new BigDecimal("10.00");
		BigDecimal totalFee = bigDecimal1.multiply(bigDecimal);
		pf.setAmmount(totalFee);
		pf.setTiming(parkingFeeDTO.getTiming());

		ParkingFee addFeeDetail = feeRepo.save(pf);

		ParkingFeeDTO pfdto = new ParkingFeeDTO();
		pfdto.setId(addFeeDetail.getId());
		pfdto.setAmmount(addFeeDetail.getAmmount());
		pfdto.setTiming(addFeeDetail.getTiming());
		pfdto.setCreatedOn(addFeeDetail.getCreatedOn());
		pfdto.setLastUpdatedOn(addFeeDetail.getLastUpdatedOn());
		return pfdto;
	}

	@Override
	public List<ParkingFeeDTO> getAllFees() {
		 List<ParkingFee> allfee = feeRepo.findAll();
		List<ParkingFeeDTO> dto = new ArrayList();

		for (ParkingFee feeDTO : allfee) {
			ParkingFeeDTO fdto = new ParkingFeeDTO();
			fdto.setId(feeDTO.getId());
			fdto.setAmmount(feeDTO.getAmmount());
			fdto.setTiming(feeDTO.getTiming());
			fdto.setCreatedOn(feeDTO.getCreatedOn());
			fdto.setLastUpdatedOn(feeDTO.getLastUpdatedOn());

			dto.add(fdto);
		}
		return dto;
	}

}
