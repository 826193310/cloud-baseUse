package com.su.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname SentinelMainApp8404
 * @Description TODO
 * @Date 2020/11/3 13:03
 * @Created by SGZ
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMainApp8404 {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMainApp8404.class, args);
    }
}
