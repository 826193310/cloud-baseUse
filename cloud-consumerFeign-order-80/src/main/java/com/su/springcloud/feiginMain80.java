package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname feiginMain80
 * @Description TODO
 * @Date 2020/8/15 0:14
 * @Created by SGZ
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients  // 开启Feign服务
public class feiginMain80 {
    public static void main(String[] args) {
        SpringApplication.run(feiginMain80.class, args);
    }
}
