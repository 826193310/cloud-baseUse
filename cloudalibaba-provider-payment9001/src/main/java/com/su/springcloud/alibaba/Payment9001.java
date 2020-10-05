package com.su.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname Payment9001
 * @Description TODO
 * @Date 2020/10/5 11:02
 * @Created by SGZ
 */
@SpringBootApplication
@EnableDiscoveryClient
public class Payment9001 {
    public static void main(String[] args) {
        SpringApplication.run(Payment9001.class, args);
    }
}
