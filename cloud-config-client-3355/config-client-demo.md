
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

# 77_Config动态刷新之手动版

## 目前存在的问题

通过上面的客户端程序，目前配置是可以成功获取，但是如果别人更新了配置，如果不重启项目的话是没办法更新配置的



## 手动刷新 config 配置

### 查看是否已经引入了 actuator 

查看客户端是否已经存在了 actuator 依赖

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

###  修改 客户端 config-client-3355 的 bootstrap.yml 文件，暴露监控端点

cloud-config-client-3355 module 修改 bootstrap.yml 增加以下配置

```yaml
# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### config-client-3355的 controller 增加 @RefreshScope 注解

```java
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfo;
    }
}
```

## 测试手动刷新

### 启动模块（请按照顺序启动）

 1、eureka 7001

2、configCenter3344

### 访问 config-server

访问： http://localhost:3344/master/config-dev.yml

获得结果：

```yaml
config:
  info: master branch, springcloud-config/config-dev.yml version=1
```



github 修改 master 分支下 config-dev.yml 的内容如下

```yaml
config:
  info: master branch, springcloud-config/config-dev.yml version=2
```



此时再次访问：http://localhost:3344/master/config-dev.yml

获得结果：

```yaml
config:
  info: master branch, springcloud-config/config-dev.yml version=2
```



### 启动 config-client-3355

访问：http://localhost:3355/configInfo，得到以下内容：

master branch, springcloud-config/config-dev.yml version=2



### 再次修改github  master 分支下 config-dev.yml 的内容如下



config:
  info: master branch, springcloud-config/config-dev.yml version=3

### 再次访问 config-server

访问：http://localhost:3344/master/config-dev.yml，得到以下内容：

```yaml
config:
  info: master branch, springcloud-config/config-dev.yml version=3
```

可以看到， config-server 没重启的情况下，即便github改了数据，主要访问都会从最新的数据进行拉取



### 再次访问 config-client-3355

访问：http://localhost:3355/configInfo，得到以下内容：

```
master branch, springcloud-config/config-dev.yml version=2
```



可以看到上面的内容并没有进行刷新

### 请求 curl -X POST "http://localhost:3355/actuator/refresh" 手动刷新

在命令行窗口使用 curl 进行 POST 请求， curl -X POST "http://localhost:3355/actuator/refresh"  

返回：

```
["config.client.version","config.info"]
```



### 手动刷新后再次访问 config-client-3355

访问：http://localhost:3355/configInfo，得到以下内容：

```
master branch, springcloud-config/config-dev.yml version=3
```

可以看到， 内容已经被更新



## 手动刷新注意点

你在github 改变了数据之后，在本地一定要先访问config 进行拉取 http://localhost:3344/master/config-dev.yml   ， 当config 模块拉取到数据回来之后，其它节点才可以进行请求刷新数据成功，否则client端依旧还是没有改变的

## 手动刷新存在的问题

1、手动刷新在一般的小公司，模块不多的情况下可以满足，但是如果有多个客户端 ，那么每个微服务都需要执行一个 POST请求，

2、没办法大范围自动刷新

3、没办法一次通知，处处生效

# 78_82 使用cloud bus + ribbitMQ 进行配置文件刷新

## 先决条件

使用 spring cloud bus 消息总线支持 kafka 和rabbit MQ 消息代理，我使用 rabbitMQ,安装 rabbitMQ ， 安装教程自行百度



## 业务编码

### cloud-config-3344

#### pom

```java
<!-- 添加消息总线 rabbitMQ 支持 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```



#### application.yml 增加配置, 注意 rabbitmq 的前面是spring 的 ， 注意yml 

```yaml
spring:
  # rabbitmq 相关配置
  rabbitmq:
    host: localhost
    port: 5672 # 注意管理界面的端口和 rabbitmq 的端口的区别， 管理界面的是 15672， mq 的是 5672
    username: guest
    password: guest

# rabbitmq 相关配置， 暴露 bus 刷新配置的断电
management:
  endpoints: # 暴露 bus 刷新配置的端点
    web:
      exposure:
        include: "bus-refresh"
```



### cloud-config-client-3355

#### pom

```java
<!-- 添加消息总线 rabbitMQ 支持 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bus-amqp</artifactId>
        </dependency>
```

### bootstrap.yml

增加以下内容，注意 rabbitmq 是基于spring前缀的

```yaml
spring:
  rabbitmq:
    host: localhost
    port: 5672 # 注意管理界面的端口和 rabbitmq 的端口的区别， 管理界面的是 15672， mq 的是 5672
    username: guest
    password: guest
```



### 基于 3355 新建 3366 项目，两个为集群项目

除了端口改为3366， 其它基本一致

####  pom

```java
<dependencies>
    <!-- 添加消息总线 rabbitMQ 支持 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-bus-amqp</artifactId>
    </dependency>
    <!-- springcloud-config 依赖，如果是 client 端，只需spring-cloud-config 即可，server 端的话是 spring-cloud-config-server -->
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



#### bootstrap.yml

```yaml
server:
  port: 3366

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
  # rabbitmq 相关配置
  rabbitmq:
    host: localhost
    port: 5672 # 注意管理界面的端口和 rabbitmq 的端口的区别， 管理界面的是 15672， mq 的是 5672
    username: guest
    password: guest

# eureka 地址
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/

# 暴露监控端点
management:
  endpoints:
    web:
      exposure:
        include: "*"
```



#### 主类 ConfigClientMain3366

```
package com.su.springcloud;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Classname ConfigClientMain3366
 * @Description ConfigClient 启动主类
 * @Date 2020/9/19 19:19
 * @Created by SGZ
 */
@SpringBootApplication
@EnableEurekaClient  
public class ConfigClientMain3366 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientMain3366.class, args);
    }
}
```



#### 测试 controller 

```java
package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ConfigClientController
 * @Description TODO
 * @Date 2020/9/20 19:18
 * @Created by SGZ
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${config.info}")
    private String configInfo;

    @Value("${server.port}")
    private String serverPort;

    /**
     *
     *@Description: http://localhost:3355/configInfo
     *@param: null
     *@Author: SGZ
     *@Date: 2020/9/20
     *@return:
     *
     **/
    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return "serverPort: " + serverPort + " configInfo" + configInfo;
    }
}
```



## 测试通过spring cloud bus + rabbitmq 刷新

### 启动模块（请按照顺序启动）

rabbitmq 启动

 1、eureka 7001

2、configCenter3344

3、config-client-3355

4、config-client-3366



### 访问各模块

http://localhost:3355/configInfo

```
master branch, springcloud-config/config-dev.yml version=5
```



http://localhost:3366/configInfo

```
serverPort: 3366 configInfomaster branch, springcloud-config/config-dev.yml version=5
```



修改github  config-dev.yml 信息为：

```
config:
  info: master branch, springcloud-config/config-dev.yml version=6
```



### 使用 POST 访问 config 的 bus-refresh, 对所有节点进行更新

curl -X POST "http://localhost:3344/actuator/bus-refresh"



### 更新所有节点后访问

http://localhost:3355/configInfo

```
master branch, springcloud-config/config-dev.yml version=6
```

http://localhost:3366/configInfo

```
serverPort: 3366 configInfomaster branch, springcloud-config/config-dev.yml version=6
```

通过以上发现，通过请求  http://localhost:3344/actuator/bus-refresh 之后，两个 client 节点均已经更新配置



### 使用 POST 访问 config 的 bus-refresh/{destination}, 对局部节点更新

#### 修改github   config-dev.yml 

修改github  config-dev.yml 信息为：

```yaml
config:
  info: master branch, springcloud-config/config-dev.yml version=7
```



#### 只刷新 3355 节点的配置文件

curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3355"

访问： http://localhost:3344/master/config-dev.yml 更新到 config-server



#### 再次访问 3355  http://localhost:3355/configInfo

结果：

```
master branch, springcloud-config/config-dev.yml version=7
```



#### 再次访问 3366 http://localhost:3366/configInfo

结果：

```
serverPort: 3366 configInfomaster branch, springcloud-config/config-dev.yml version=6
```



看到，3355改变之后，3366并没有进行改变

