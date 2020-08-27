package com.su.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname OrderHystrixService
 * @Description TODO
 * @Date 2020/8/27 10:02
 * @Created by SGZ
 */
@Component
@FeignClient(value = "cloud-payment-service", fallback = OrderHystrixServiceFallbackImpl.class)
public interface OrderHystrixService {
    @GetMapping(value = "/payment/getInfoId/{id}")
    String getInfo(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/timeout")
    String timeOut();
}
