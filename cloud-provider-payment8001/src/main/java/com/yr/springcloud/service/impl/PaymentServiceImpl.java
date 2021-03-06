package com.yr.springcloud.service.impl;

import com.yr.springcloud.dao.PaymentDao;
import com.yr.springcloud.entities.Payment;
import com.yr.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment getPaymentById(Long id) {

        return paymentDao.getPaymentById(id);
    }

    @Override
    public int create(Payment payment) {

        return paymentDao.create(payment);
    }
}
