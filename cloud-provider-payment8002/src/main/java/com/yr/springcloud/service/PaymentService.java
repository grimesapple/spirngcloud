package com.yr.springcloud.service;

import com.yr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;


public interface PaymentService {


    Payment getPaymentById(@Param("id") Long id);

    public int create(Payment payment);
}
