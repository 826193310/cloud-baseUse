package com.su.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Classname NacosOrderMain83
 * @Description TODO
 * @Date 2020/10/5 12:06
 * @Created by SGZ
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosOrderMain83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrderMain83.class, args);
    }
}
