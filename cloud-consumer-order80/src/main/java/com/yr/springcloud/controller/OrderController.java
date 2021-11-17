package com.yr.springcloud.controller;


import com.yr.springcloud.entities.CommonResult;
import com.yr.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    //定义支付者的URL
//    public static final String PAYMENT_URL = "http://localhost:8001";

    public static final String PAYMENT_URL = "http://CLOUDE-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("消费者传入的数据："+payment);

//        HttpEntity<String> formEntity = new HttpEntity<String>(payment.toString(), new HttpHeaders());
        String serial = payment.getSerial();

//        return restTemplate.postForObject(PAYMENT_URL+"/payment/create?serial={serial}",null,CommonResult.class,serial);
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
