package com.nt.payment_service.controller;

import com.nt.payment_service.requestDTO.OrderRequestDTO;
import com.nt.payment_service.responseDTO.OrderResponseDTO;
import com.nt.payment_service.service.RazorpayService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RazorPayController {

	@Autowired
	private RazorpayService razorpayService;

	@PostMapping(value="/home", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderResponseDTO order(@RequestBody OrderRequestDTO orderRequestDTO) {
		return razorpayService.createOrder(orderRequestDTO);
	}
	
	
}
