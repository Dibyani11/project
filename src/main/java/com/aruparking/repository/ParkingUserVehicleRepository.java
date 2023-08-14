package com.aruparking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aruparking.model.ParkingUserVehicle;

public interface ParkingUserVehicleRepository extends JpaRepository<ParkingUserVehicle, Long> {

	List<ParkingUserVehicle> findByParkingUserIdAndDefaultVehicle(long userId, boolean defaultVehicle);

	ParkingUserVehicle findByParkingUserIdAndVehicleNo(long userId, String vehicleNo);

	ParkingUserVehicle findById(long id);

	List<ParkingUserVehicle> findByParkingUserIdAndFavVehicle(long id, boolean b);

	List<ParkingUserVehicle> findByParkingUserId(long id);

}
