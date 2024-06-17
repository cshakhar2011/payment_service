package com.nt.payment_service.service;

import com.nt.payment_service.entity.OrderRazorpay;
import com.nt.payment_service.repository.RazorpayRepository;
import com.nt.payment_service.requestDTO.OrderRequestDTO;
import com.nt.payment_service.responseDTO.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DAOService {
    @Autowired
    private RazorpayRepository razorpayRepository;
    public OrderRazorpay saveOrderRequest(OrderRequestDTO orderRequestDTO) {
        OrderRazorpay orderRazorpay=new OrderRazorpay();
        orderRazorpay.setAmount(orderRequestDTO.getAmount());
        orderRazorpay.setCurrency(orderRequestDTO.getCurrency());
        orderRazorpay.setReceipt(orderRequestDTO.getReceipt());
        orderRazorpay.setNotes(orderRequestDTO.getNotes());
        OrderRazorpay save = razorpayRepository.save(orderRazorpay);
        System.out.print("save Request to database"+save);
        return save;
    }
    public void updateOrderRequest(OrderResponseDTO orderResponseDTO, Integer id) {
        Optional<OrderRazorpay> byId = razorpayRepository.findById(id);
        if (byId.isPresent()) {
            OrderRazorpay orderRazorpay = byId.get();
            orderRazorpay.setOrderId(orderResponseDTO.getOrderId());
            orderRazorpay.setAttempts(orderResponseDTO.getAttempts());
            orderRazorpay.setStatus(orderResponseDTO.getStatus());
            orderRazorpay.setCreated_at(orderResponseDTO.getCreated_at());
            orderRazorpay.setAmount_paid(orderResponseDTO.getAmount_paid());
            OrderRazorpay save = razorpayRepository.save(orderRazorpay);
            System.out.print("save Request to database"+save.toString());
        }else {
            System.out.print("Getting any Error...");
        }
    }
}
