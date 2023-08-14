package com.aruparking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aruparking.model.ParkingOrder;

public interface ParkingOrderRepository extends JpaRepository<ParkingOrder, Long> {

	List<ParkingOrder> findAllByParkingUserId(long id);

	List<ParkingOrder> findAllByParkingSlotsParkingZonesId(long id);

	List<ParkingOrder> findAllByParkingSlotsId(long id);

}
