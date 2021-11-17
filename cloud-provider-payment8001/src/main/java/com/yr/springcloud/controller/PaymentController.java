package com.yr.springcloud.controller;


import com.yr.springcloud.entities.CommonResult;
import com.yr.springcloud.entities.Payment;
import com.yr.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {


    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;



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


    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("***** element"+service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUDE-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId()+"  "+instance.getHost()+"  "+instance.getPort()+"  "+instance.getUri());
        }
        return this.discoveryClient;
    }



}
