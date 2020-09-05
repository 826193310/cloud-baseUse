package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Classname HystrixDashBoard9001
 * @Description Hystrix DashBoard Demo
 * @Date 2020/9/5 10:46
 * @Created by SGZ
 */
@SpringBootApplication
@EnableHystrixDashboard // Hystrix 图形化注解， 启动后访问 http://localhost:9001/hystrix
public class HystrixDashBoard9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashBoard9001.class, args);
    }
}
