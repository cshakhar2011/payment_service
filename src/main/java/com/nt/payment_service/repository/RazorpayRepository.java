package com.nt.payment_service.repository;


import com.nt.payment_service.entity.OrderRazorpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazorpayRepository extends JpaRepository<OrderRazorpay,Integer>
{
}
