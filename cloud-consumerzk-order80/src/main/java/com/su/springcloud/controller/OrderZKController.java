package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Classname OrderZKController
 * @Description TODO
 * @Date 2020/8/7 14:06
 * @Created by SGZ
 */
@RestController
public class OrderZKController {

    private final static String INVOKE_URL = "http://cloud-payment-service";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String getOrder() {
        String rs = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        return rs;
    }
}
