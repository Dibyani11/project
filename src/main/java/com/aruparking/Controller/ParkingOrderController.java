package com.aruparking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruparking.DTO.ParkingOrderDTO;
import com.aruparking.service.ParkingOrderService;

@RestController
@RequestMapping("/order/add")
public class ParkingOrderController {

	@Autowired
	private ParkingOrderService parkingOrderService;

	// http:localhost:8088/order/add
	@PostMapping
	ParkingOrderDTO addOrder(@RequestBody ParkingOrderDTO parkingOrderDTO) {
		return parkingOrderService.placingOrder(parkingOrderDTO);
	}
}
