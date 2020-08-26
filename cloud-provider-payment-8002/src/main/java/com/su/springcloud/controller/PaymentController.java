package com.su.springcloud.controller;

import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import com.su.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname PaymentController
 * @Description TODO
 * @Date 2020/7/30 0:06
 * @Created by SGZ
 */
@RestController
public class PaymentController {
    private Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        logger.info("*************插入结果：" + result);

        if (result >0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(200, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment result = paymentService.getPaymentById(id);
        System.out.println("1");
        if (result != null) {
            return new CommonResult(200, "查询成功:" + port, result);
        } else {
            return new CommonResult(200, "没有对应记录，查询ID：" + id, null);
        }
    }
}
