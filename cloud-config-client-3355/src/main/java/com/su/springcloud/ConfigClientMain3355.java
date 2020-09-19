package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Classname ConfigClientMain3355
 * @Description ConfigClient 启动主类
 * @Date 2020/9/19 19:19
 * @Created by SGZ
 */
@SpringBootApplication
@EnableEurekaClient  
public class ConfigClientMain3355 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3355.class, args);
    }
}
