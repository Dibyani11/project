package com.aruparking.serviceImpl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingUserDTO;
import com.aruparking.model.ParkingUser;
import com.aruparking.repository.ParkingUserRepository;
import com.aruparking.service.ParkingUserService;

@Service
public class ParkingUserServiceImpl implements ParkingUserService {

	@Autowired
	ParkingUserRepository userRepo;

	@Override
	public Object addUserDetails(ParkingUserDTO userDTO) {
		ParkingUser prkuser = new ParkingUser();

		prkuser.setFirstName(userDTO.getFirstName());
		prkuser.setLastName(userDTO.getLastName());
		prkuser.setEmailId(userDTO.getEmailId());
		prkuser.setdOB(userDTO.getdOB());
		prkuser.setBalance(new BigDecimal("100.00"));

		ParkingUser addUser = userRepo.save(prkuser);

		ParkingUserDTO udto = new ParkingUserDTO();
		udto.setId(addUser.getId());
		udto.setFirstName(addUser.getFirstName());
		udto.setLastName(addUser.getLastName());
		udto.setEmailId(addUser.getEmailId());
		udto.setdOB(addUser.getdOB());
		udto.setBalance(addUser.getBalance());
		udto.setCreatedOn(addUser.getCreatedOn());
		udto.setLastUpdatedOn(addUser.getLastUpdatedOn());

		return udto;
	}

}
