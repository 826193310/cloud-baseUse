# 113_Sentinel初始化监控

## 搭建模块

### 创建module 

创建名为 cloudalibaba-sentinel-service8401 的module 模块

### 引进pom

```xml
<!--springcloud alibaba nacos-->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
        </dependency>

        <!--springcloud alibaba sentinel-datasource-nacos, 后续做持久化用到-->
        <dependency>
            <groupId>com.alibaba.csp</groupId>
            <artifactId>sentinel-datasource-nacos</artifactId>
        </dependency>

        <!--springcloud alibaba sentinel -->
        <dependency>
            <groupId>com.alibaba.cloud</groupId>
            <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
        </dependency>

        <!--openfeign-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>

        <dependency>
            <groupId>com.su.springcloud</groupId>
            <artifactId>cloud-api-commons</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>true</scope>
        </dependency>
```



### application.yml

```yaml
server:
  port: 8401

spring:
  application:
    name: cloudalibaba-sentinel-service
  cloud:
    nacos:
      # nacos 注册地址
      discovery:
        server-addr: localhost:8848
    # sentinel 相关配置
    sentinel:
      transport:
        # 配置 sentinel dashboard 地址
        dashboard: localhost:8080
        # 默认 8719 端口，假如被占用会自动从8719开始依次 +1 扫描， 直至找到未被占用的端口
        port: 8719

management:
  endpoints:
    web:
      exposure:
        include: '*'
```



### 主启动类

```
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

```



### controller 类

```java
package com.su.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname FlowLimitController
 * @Description sentinel demo 项目
 * @Date 2020/11/3 13:11
 * @Created by SGZ
 */
@RestController
public class FlowLimitController {

    /**
    *
    *@Description: http://localhost:8401/testA
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/3
    *@return:
    *
    **/
    @GetMapping("/testA")
    public String testA() {
        return "----testA";
    }

    /**
     *
     *@Description: http://localhost:8401/testB
     *@param: null
     *@Author: SGZ
     *@Date: 2020/11/3
     *@return:
     *
     **/
    @GetMapping("/testB")
    public String testB() {
        return "----testB";
    }
}

```

## 测试

### 启动模块

启动 nacos

启动 sentinel

### 由于sentinel是懒加载，默认启动之后不会监控流量，程序启动之后，访问：[*http://localhost:8401/testA*](http://localhost:8401/testA)  *，多访问几次*



*然后就可以在 sentinel 的中台看到相关的信息*













