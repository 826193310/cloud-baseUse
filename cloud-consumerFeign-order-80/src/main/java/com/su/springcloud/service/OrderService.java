package com.su.springcloud.service;

import com.su.springcloud.configuration.FeignConfiguration;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname OrderService
 * @Description TODO
 * @Date 2020/8/15 0:18
 * @Created by SGZ
 */
@Component
@FeignClient(value = "cloud-payment-service")
public interface OrderService {
    @GetMapping(value = "/payment/getInfo")
    String getInfo();

    @GetMapping(value = "/payment/timeout")
    String timeOut();

}
