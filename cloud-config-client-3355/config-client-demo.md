
# 76_Config客户端配置与测试

## 新建 module  cloud-config-client-3355

## pom依赖

```java
<dependencies>
        <!-- springcloud-config 依赖，如果是 client 端，只需spring-cloud-starter-config 即可，server 端的话是 spring-cloud-config-server -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-config</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
        <!--引入自己定义的API通用包，可以使用Payment 支付Entity-->
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
    </dependencies>
```



## 新建系统级配置bootstrap.xml 文件到 cloud-config-client-3355

bootstrap.yml  是系统级的加载配置文件，而application.yml 是用户级别的资源配置项，所以 bootstrap.yml 优先级别更加高



```yaml
server:
  port: 3355

spring:
  application:
    name: config-client
  cloud:
    # config 客户端配置
    config:
      label: master # 分支名称
      name: config # 配置文件名称
      profile: dev # 读取后缀名称， 对应的就是项目的 config-dev.yml 文件的
      uri: http://localhost:3344 # 配置中心地址

# eureka 地址
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
```



## 创建启动主类  com.su.springcloud.ConfigClientMain3355

```java
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

```

## 增加测试 controller

```
package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ConfigClientController
 * @Description 测试 ConfigClient
 * @Date 2020/9/20 0:22
 * @Created by SGZ
 */
@RestController
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
```



## 测试 config-client

### 启动模块（请按照顺序启动）

 1、eureka 7001

2、configCenter3344

3、config-client-3355

### 访问 http://localhost:3355/configInfo

返回如下：

```
master branch, springcloud-config/config-dev.yml version=1
```

正常获取