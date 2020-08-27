package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Classname HystrixPaymentController
 * @Description 为 hystrix demo 使用
 * @Date 2020/8/27 10:00
 * @Created by SGZ
 */
@RestController
public class HystrixPaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/payment/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.port;
    }
}
