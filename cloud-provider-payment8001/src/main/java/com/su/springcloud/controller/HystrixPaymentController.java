package com.su.springcloud.controller;

import com.su.springcloud.service.HsytrixPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    HsytrixPaymentService hsytrixPaymentService;

    @GetMapping(value = "/payment/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.port;
    }

    /**
    *
    *@Description: 服务熔断测试
     * 正确调用：http://localhost:8001/paymentcircuit/3
     * 错误调用（ID负数）：http://localhost:8001/paymentcircuit/-3
    *@param: null
    *@Author: SGZ
    *@Date: 2020/9/4
    *@return:
    *
    **/
    @GetMapping("/paymentcircuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = hsytrixPaymentService.paymentCircuitBreaker(id);
        System.out.println("result:" + result);
        return result;
    }
}
