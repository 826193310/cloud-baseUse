package com.su.springcloud.controller;

import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import com.su.springcloud.service.GiteeFeignClient;
import com.su.springcloud.service.JuejinFeignService;
import com.su.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderController
 * @Description TODO
 * @Date 2020/8/15 0:17
 * @Created by SGZ
 */
@RestController
public class OrderController {

    static {
        // 设置本地代理，方便查看本地服务之间的请求
       /* System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");*/
    }

    @Autowired
    OrderService orderService;

    @Autowired
    JuejinFeignService juejinFeignService;

    @Autowired
    GiteeFeignClient giteeFeignClient;

    /**
    *
    *@Description: http://localhost/feign/consumer/payment/get/1
    *@param: null
    *@Author: SGZ
    *@Date: 2020/8/19
    *@return:
    *
    **/
    @GetMapping("/feign/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        long before = System.currentTimeMillis();
        String result = orderService.getInfo();
        long after = System.currentTimeMillis();
        System.out.println("耗时：" + (after - before));
        return null;
    }

    /**
    *
    *@Description: http://localhost//feign/consumer/payment/timeout
    *@param: null
    *@Author: SGZ
    *@Date: 2020/8/19
    *@return:
    *
    **/
    @GetMapping("/feign/consumer/payment/timeout")
    public CommonResult<Payment> timeoutTest() {
        long before = System.currentTimeMillis();
        String result = orderService.timeOut();
        long after = System.currentTimeMillis();
        System.out.println("耗时：" + (after - before));
        return null;
    }

}
