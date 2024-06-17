package com.nt.payment_service.service;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RazorpayClientAuthentication {
    @Value("${key_id}")
    private String key;
    @Value("${key_secret}")
    private String value;


    public RazorpayClient authentication() {
        RazorpayClient razorpayClient = null;
        try {
             razorpayClient = new RazorpayClient(key, value);
        } catch (RazorpayException e)
        {
            e.printStackTrace();
        }
        return  razorpayClient;
    }
}
