package com.su.springcloud.service;

import com.su.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname PaymentService
 * @Description TODO
 * @Date 2020/7/30 0:04
 * @Created by SGZ
 */
public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);
}
