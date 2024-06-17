package com.nt.payment_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.payment_service.entity.OrderRazorpay;
import com.nt.payment_service.requestDTO.OrderRequestDTO;
import com.nt.payment_service.responseDTO.OrderResponseDTO;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RazorpayService {
    @Autowired
    private DAOService daoService;
    @Autowired
    private RazorpayClientAuthentication razorpayClientAuthentication;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){
        OrderRazorpay orderRazorpay = daoService.saveOrderRequest(orderRequestDTO);
        RazorpayClient razorpayClient = razorpayClientAuthentication.authentication();
        OrderResponseDTO orderResponseDTO=null;
            if(!Objects.isNull(razorpayClient)) {
                JSONObject orderRequest = new JSONObject();
                orderRequest.put("amount", orderRequestDTO.getAmount()*100);
                orderRequest.put("currency", orderRequestDTO.getCurrency());
                orderRequest.put("receipt",orderRequestDTO.getReceipt());
                JSONObject notes = new JSONObject();
                notes.put("notes_key_1",orderRequestDTO.getNotes());
                orderRequest.put("notes", notes);
                try {
                    orderResponseDTO = mapper(razorpayClient.orders.create(orderRequest),orderRazorpay.getId());
                } catch (RazorpayException e) {
                    throw new RuntimeException(e);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            return orderResponseDTO;
    }
    private OrderResponseDTO mapper(Order order, Integer id) throws JsonProcessingException {
        OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
        orderResponseDTO.setAmount(Integer.valueOf(order.get("amount").toString())/100);
        orderResponseDTO.setCreated_at(order.get("created_at").toString());
        orderResponseDTO.setCurrency(order.get("currency").toString());
        orderResponseDTO.setAttempts(order.get("attempts").toString());
        orderResponseDTO.setReceipt(order.get("receipt").toString());
        orderResponseDTO.setAmount_paid(order.get("amount_paid").toString());
        orderResponseDTO.setOrderId(order.get("id").toString());
        ObjectMapper mapper = new ObjectMapper();
        orderResponseDTO.setNotes(mapper.readTree(order.get("notes").toString()).get("notes_key_1").asText());
        daoService.updateOrderRequest(orderResponseDTO,id);
        return orderResponseDTO;
    }

}
