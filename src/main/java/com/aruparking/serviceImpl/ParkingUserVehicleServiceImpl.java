package com.aruparking.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aruparking.DTO.ParkingSlotsDTO;
import com.aruparking.DTO.ParkingUserVehicleDTO;
import com.aruparking.model.ParkingUser;
import com.aruparking.model.ParkingUserVehicle;
import com.aruparking.repository.ParkingUserRepository;
import com.aruparking.repository.ParkingUserVehicleRepository;
import com.aruparking.service.ParkingUserVehicleService;

@Service
public class ParkingUserVehicleServiceImpl implements ParkingUserVehicleService {

	@Autowired
	ParkingUserVehicleRepository parkingUserVehicleRepo;

	@Autowired
	ParkingUserRepository parkingUserRepo;

	@Override
	public ParkingUserVehicleDTO addVehicle(ParkingUserVehicleDTO parkingUserVehicleDTO) {

		ParkingUser userId = parkingUserRepo.findById(parkingUserVehicleDTO.getUserId());

		if (userId != null) {
			ParkingUserVehicle userVh = parkingUserVehicleRepo.findByParkingUserIdAndVehicleNo(parkingUserVehicleDTO.getUserId(), parkingUserVehicleDTO.getVehicleNo());

			if (userVh == null) {

				ParkingUserVehicle pv = new ParkingUserVehicle();

				pv.setParkingUser(userId);
				pv.setVehicleNo(parkingUserVehicleDTO.getVehicleNo());
				pv.setVehicleName(parkingUserVehicleDTO.getVehicleName());
				pv.setStatus(1);
				pv.setDefaultVehicle(parkingUserVehicleDTO.isDefaultVehicle());
				pv.setFavVehicle(parkingUserVehicleDTO.isFavVehicle());

				if (parkingUserVehicleDTO.isDefaultVehicle() == true) {

					List<ParkingUserVehicle> userVehicles = parkingUserVehicleRepo.findByParkingUserIdAndDefaultVehicle(parkingUserVehicleDTO.getUserId(),parkingUserVehicleDTO.isDefaultVehicle());

					for (ParkingUserVehicle vehicle : userVehicles) {
						vehicle.setDefaultVehicle(false);
						parkingUserVehicleRepo.save(vehicle);
					}

					ParkingUserVehicle addvehicle = parkingUserVehicleRepo.save(pv);
					ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

					dto.setId(addvehicle.getId());
					dto.setUserId(addvehicle.getParkingUser().getId());
					dto.setVehicleNo(addvehicle.getVehicleNo());
					dto.setCreatedOn(addvehicle.getCreatedOn());
					dto.setUpdatedOn(addvehicle.getLastUpdatedOn());
					dto.setStatus(addvehicle.getStatus());
					dto.setDefaultVehicle(addvehicle.isDefaultVehicle());
					dto.setFavVehicle(addvehicle.isFavVehicle());
					dto.setVehicleName(addvehicle.getVehicleName());
					return dto;

				} else {
					ParkingUserVehicle addvehicle = parkingUserVehicleRepo.save(pv);
					ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

					dto.setId(addvehicle.getId());
					dto.setUserId(addvehicle.getParkingUser().getId());
					dto.setVehicleNo(addvehicle.getVehicleNo());
					dto.setCreatedOn(addvehicle.getCreatedOn());
					dto.setUpdatedOn(addvehicle.getLastUpdatedOn());
					dto.setStatus(addvehicle.getStatus());
					dto.setDefaultVehicle(addvehicle.isDefaultVehicle());
					dto.setFavVehicle(addvehicle.isFavVehicle());
					dto.setVehicleName(addvehicle.getVehicleName());
					return dto;
				}
			} else {
				throw new RuntimeException("Duplicate Vehicle NO. For UserId ");
			}
		} else {
			throw new RuntimeException("UserId is not Present .");
		}
	}

	@Override
	public ParkingUserVehicleDTO updateVehicle(ParkingUserVehicleDTO parkingUserVehicleDTO) {

		ParkingUserVehicle pv = parkingUserVehicleRepo.findById(parkingUserVehicleDTO.getId());

		if (pv != null) {
			ParkingUserVehicle userVh = parkingUserVehicleRepo.findByParkingUserIdAndVehicleNo(parkingUserVehicleDTO.getUserId(), parkingUserVehicleDTO.getVehicleNo());
			if (userVh == null) {
				pv.setVehicleNo(parkingUserVehicleDTO.getVehicleNo());
				pv.setVehicleName(parkingUserVehicleDTO.getVehicleName());
				pv.setDefaultVehicle(parkingUserVehicleDTO.isDefaultVehicle());
				pv.setFavVehicle(parkingUserVehicleDTO.isFavVehicle());

				if (parkingUserVehicleDTO.isDefaultVehicle() == true) {

				List<ParkingUserVehicle> userVehicles = parkingUserVehicleRepo.findByParkingUserIdAndDefaultVehicle(parkingUserVehicleDTO.getUserId(),parkingUserVehicleDTO.isDefaultVehicle());

					for (ParkingUserVehicle vehicle : userVehicles) {
						vehicle.setDefaultVehicle(false);
						parkingUserVehicleRepo.save(vehicle);
					}

					ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

					dto.setId(pv.getId());
					dto.setUserId(pv.getParkingUser().getId());
					dto.setVehicleNo(pv.getVehicleNo());
					dto.setCreatedOn(pv.getCreatedOn());
					dto.setUpdatedOn(pv.getLastUpdatedOn());
					dto.setStatus(pv.getStatus());
					dto.setDefaultVehicle(pv.isDefaultVehicle());
					dto.setFavVehicle(pv.isFavVehicle());
					dto.setVehicleName(pv.getVehicleName());
					return dto;

				} else {
					ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

					dto.setId(pv.getId());
					dto.setUserId(pv.getParkingUser().getId());
					dto.setVehicleNo(pv.getVehicleNo());
					dto.setCreatedOn(pv.getCreatedOn());
					dto.setUpdatedOn(pv.getLastUpdatedOn());
					dto.setStatus(pv.getStatus());
					dto.setDefaultVehicle(pv.isDefaultVehicle());
					dto.setFavVehicle(pv.isFavVehicle());
					dto.setVehicleName(pv.getVehicleName());
					return dto;
				}
			} else {
				throw new RuntimeException("Duplicate VehicleNO. For UserId ");
			}
		}
		return null;
	}

	@Override
	public Object deleteVehicleById(long id) {

		ParkingUserVehicle parkingVehicle = parkingUserVehicleRepo.findById(id);
		
		if (parkingVehicle != null) {
			parkingVehicle.setStatus(0);
			parkingUserVehicleRepo.save(parkingVehicle);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "Deleted successfully .");
			return map;
		}else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("status", "Account doesn't exists.");
			return map;
		}
	}

	@Override
	public List<ParkingUserVehicleDTO> getSavedVehicleById(long id) {
		
          ParkingUser parkingUser = parkingUserRepo.findById(id);
          if (parkingUser != null) {
      
        	List<ParkingUserVehicle> allvehicle = parkingUserVehicleRepo.findByParkingUserId(id); 
      		List<ParkingUserVehicleDTO> vdto = new ArrayList<ParkingUserVehicleDTO>();

        	  
          for (ParkingUserVehicle vehicle : allvehicle) {
              ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

              dto.setId(vehicle.getId());
              dto.setVehicleNo(vehicle.getVehicleNo());
              dto.setCreatedOn(vehicle.getCreatedOn());
              dto.setStatus(vehicle.getStatus());
              dto.setDefaultVehicle(vehicle.isDefaultVehicle());
              dto.setFavVehicle(vehicle.isFavVehicle());
              dto.setVehicleName(vehicle.getVehicleName());
              dto.setUpdatedOn(vehicle.getLastUpdatedOn());
              dto.setUserId(vehicle.getParkingUser().getId());
              vdto.add(dto);
		}
       return vdto;
	}else {
		throw new RuntimeException("user is not present.");
	}
	}

	@Override
	public List<ParkingUserVehicleDTO> getFavoriteUserVehicle(long id) {

		ParkingUser parkingUser = parkingUserRepo.findById(id);
        if (parkingUser != null) {
        	
        	List<ParkingUserVehicle> favvehicle = parkingUserVehicleRepo.findByParkingUserIdAndFavVehicle(id,true);
           List<ParkingUserVehicleDTO> allv = new ArrayList();
           
           for (ParkingUserVehicle vehicle : favvehicle) {
               ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

               dto.setId(vehicle.getId());
               dto.setVehicleNo(vehicle.getVehicleNo());
               dto.setCreatedOn(vehicle.getCreatedOn());
               dto.setStatus(vehicle.getStatus());
               dto.setDefaultVehicle(vehicle.isDefaultVehicle());
               dto.setFavVehicle(vehicle.isFavVehicle());
               dto.setVehicleName(vehicle.getVehicleName());
               dto.setUpdatedOn(vehicle.getLastUpdatedOn());
               dto.setUserId(vehicle.getParkingUser().getId());

     		
               allv.add(dto);
 		}
        return allv;
 	}else {
 		throw new RuntimeException("user is not present.");
 	}
 	}

	@Override
	public List<ParkingUserVehicleDTO> getDefaultUserVehicle(long id) {
		ParkingUser parkingUser = parkingUserRepo.findById(id);
        if (parkingUser != null) {
        	
        	List<ParkingUserVehicle> defaultvehicle = parkingUserVehicleRepo.findByParkingUserIdAndDefaultVehicle(id,true);
           List<ParkingUserVehicleDTO> allv = new ArrayList();
           
           for (ParkingUserVehicle vehicle : defaultvehicle) {
               ParkingUserVehicleDTO dto = new ParkingUserVehicleDTO();

               dto.setId(vehicle.getId());
               dto.setVehicleNo(vehicle.getVehicleNo());
               dto.setCreatedOn(vehicle.getCreatedOn());
               dto.setStatus(vehicle.getStatus());
               dto.setDefaultVehicle(vehicle.isDefaultVehicle());
               dto.setFavVehicle(vehicle.isFavVehicle());
               dto.setVehicleName(vehicle.getVehicleName());
               dto.setUpdatedOn(vehicle.getLastUpdatedOn());
               dto.setUserId(vehicle.getParkingUser().getId());

               allv.add(dto);
 		}
        return allv;
 	}else {
 		throw new RuntimeException("user is not present.");
 	}
	}
}