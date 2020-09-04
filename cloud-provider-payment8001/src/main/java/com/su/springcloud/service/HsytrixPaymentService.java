package com.su.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname HsytrixPaymentService
 * @Description TODO
 * @Date 2020/9/4 22:15
 * @Created by SGZ
 */
@Component
public class HsytrixPaymentService {


    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸

    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("********* id 不能为负数");
        }

        String serialNumaber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t " + "调用成功，流水号：" + serialNumaber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "ID 不能为负数， 请稍后重试, id:" + id;
    }
}
