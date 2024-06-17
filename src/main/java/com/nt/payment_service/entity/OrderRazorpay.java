package com.nt.payment_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class OrderRazorpay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer amount;
    private String amount_paid;
    private String notes;
    private String created_at;
    private String currency;
    private String receipt;
    private String orderId;
    private String attempts;
    private String status;


}

