package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname PaymentMain8001
 * @Description 主启动类
 * @Date 2020/7/29 17:48
 * @Created by SGZ
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class PaymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8001.class, args);
    }
}
