package com.yr.springcloud.dao;

import com.yr.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {

    Payment getPaymentById(@Param("id") Long id);

    public int create(Payment payment);

}
