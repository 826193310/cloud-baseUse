package com.su.springcloud.service.impl;

import com.su.springcloud.dao.PaymentDao;
import com.su.springcloud.entities.Payment;
import com.su.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname PaymentServiceImpl
 * @Description TODO
 * @Date 2020/7/30 0:05
 * @Created by SGZ
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
