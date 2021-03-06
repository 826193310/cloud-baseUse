package com.su.springcloud.controller;

import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import com.su.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;

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

    @GetMapping(value = "/payment/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResult getPaymentById(@PathVariable("id") Long id, HttpServletRequest request) {
        //获取所有请求头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            //根据名称获取请求头的值
            String value = request.getHeader(name);
            System.out.println(name+"---"+value);
        }
        Payment result = paymentService.getPaymentById(id);
        result.setBs(new byte[1024 * 5]);
        /*byte[] bt = new byte[1024 * 1024 * 80];
        result.setBs(bt);
        System.out.println("1");*/
        if (result != null) {
            return new CommonResult(200, "查询成功:" + port, result);
        } else {
            return new CommonResult(200, "没有对应记录，查询ID：" + id, null);
        }
    }

    @GetMapping(value = "/payment/getInfo")
    public String getInfo(HttpServletRequest request) {
        //获取所有请求头名称， 查看是否有请求为 gzip 的
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 300; i++) {
            sb.append(i).append("");
        }
        return sb.toString();
    }

    @GetMapping(value = "/payment/timeout")
    public String timeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.port;
    }
}
