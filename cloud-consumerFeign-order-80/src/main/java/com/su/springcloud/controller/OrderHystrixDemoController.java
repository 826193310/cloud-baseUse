package com.su.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import com.su.springcloud.service.OrderHystrixService;
import com.su.springcloud.service.OrderHystrixServiceFallbackImpl;
import com.su.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Classname OrderHystrixDemoController
 * @Description Hystrix 演示controller
 * @Date 2020/8/26 22:24
 * @Created by SGZ
 */
@RestController
public class OrderHystrixDemoController {

    @Autowired
    private OrderService orderService;

    @Autowired
    OrderHystrixService orderHystrixService;

    /**
    *
    *@Description: 演示在 controller 层做服务降级，当调用服务超时的时候，进行超时的方法返回
    *@param: http://localhost/hystrix/consumer/get/1
    *@Author: SGZ
    *@Date: 2020/8/27
    *@return:
    *
    **/
    @GetMapping("/hystrix/consumer/get/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentTimeOut(@PathVariable("id") Integer id) {
        return orderService.timeOut();
    }

    /**
    *
    *@Description: 演示使用 feign 中使用 hystrix 的 fallback 功能
    *@param: http://localhost/hystrix/fallbackfeign/consumer/get/1
    *@Author: SGZ
    *@Date: 2020/8/27
    *@return:
    *
    **/
    @GetMapping("/hystrix/fallbackfeign/consumer/get/{id}")
    public String fallbackfeign(@PathVariable("id") Integer id) {
        return orderHystrixService.getInfo(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "我是消费者80,对方支付系统繁忙请10秒种后再试或者自己运行出错请检查自己,o(╥﹏╥)o";
    }
}
