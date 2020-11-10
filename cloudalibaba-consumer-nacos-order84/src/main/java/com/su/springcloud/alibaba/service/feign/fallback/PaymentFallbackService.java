package com.su.springcloud.alibaba.service.feign.fallback;

import com.su.springcloud.alibaba.service.PaymentService;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @Classname PaymentFallbackService
 * @Description TODO
 * @Date 2020/11/10 18:43
 * @Created by SGZ
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> getPayment(Long id) {
        return new CommonResult<>(888, "服务降级返回----------PaymentFallbackService");
    }
}
