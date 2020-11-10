package com.su.springcloud.alibaba.service;

import com.su.springcloud.alibaba.service.feign.fallback.PaymentFallbackService;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname PaymentService
 * @Description 测试 sentinel 整合 openFeign
 * @Date 2020/11/10 18:35
 * @Created by SGZ
 */
@FeignClient(value = "nacos-payment-provider", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @GetMapping("/paymentSQL/{id}")
    public CommonResult<Payment>  getPayment(@PathVariable("id") Long id);
}
