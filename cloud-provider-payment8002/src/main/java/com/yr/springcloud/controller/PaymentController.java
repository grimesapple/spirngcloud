package com.yr.springcloud.controller;


import com.yr.springcloud.entities.CommonResult;
import com.yr.springcloud.entities.Payment;
import com.yr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        log.info("****插入数据为："+payment+"hahahahah");
        if (i>0){
            return new CommonResult(200,"插入成功,serverPort:"+serverPort,i);
        } else {
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询的id为："+payment);
        if (payment!=null){
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,payment);
        } else {
            return new CommonResult(444,"没有对应的数据"+id,null);
        }
    }



}
