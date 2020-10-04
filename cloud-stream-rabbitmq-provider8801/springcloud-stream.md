# 87_Stream消息驱动之生产者

## 搭建模块

### 创建module 

创建名为 cloud-stream-rabbitmq-provider8801 的module 模块

### 引进pom

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
</dependency>
<!--eureka client-->
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
```



### application.yml

```yaml
server:
  port: 8801


spring:
  application:
    name: cloud-stream-provider
  cloud:
    stream:
      binders: # 在此处配置要绑定的 rabbitmq 的服务信息
        defaultRabbit: # 表示定义的名称，用户binding 整合
          type: rabbit # 消息组件类型
          environment: # 设置 rabbit mq 的相关环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        output: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的 Exchange 名称定义
          content-type: application/json # 设置消息类型，本次为json, 文本则设置为 “text/plain”
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置

eureka:
  client:
    fetch-registry: true # 表示是否将自己注册进eureka ， 默认为true
    register-with-eureka: true # 是否从 eurekaServer 抓取已有的注册信息，默认为true, 单节点无所谓，集群必须设置为true才能配合 ribbon 使用负载均衡
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
  instance:
    instance-id: send-8801.com
    prefer-ip-address: true  # eureka 上显示IP
```



### 主启动类

```
package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname StreamMQMain8801
 * @Description TODO
 * @Date 2020/10/4 12:16
 * @Created by SGZ
 */
@SpringBootApplication
public class StreamMQMain8801 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8801.class, args);
    }
}
```



### 消息接口类

```java
package com.su.springcloud.service;

/**
 * @Classname IMessageProvider
 * @Description TODO
 * @Date 2020/10/4 12:17
 * @Created by SGZ
 */
public interface IMessageProvider {

    public String send();
}
```

### 消息接口实现类

```java
package com.su.springcloud.service.impl;

import cn.hutool.core.lang.UUID;
import com.su.springcloud.service.IMessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 * @Classname MessageProviderImpl
 * @Description TODO
 * @Date 2020/10/4 12:18
 * @Created by SGZ
 */
@EnableBinding(Source.class) // 定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("serial:" + serial);
        return null;
    }
}
```



### controller 类

```java
package com.su.springcloud.controller;

import com.su.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SendMessageController
 * @Description TODO
 * @Date 2020/10/4 12:30
 * @Created by SGZ
 */
@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider messageProvider;

    /**
    *
    *@Description: http://localhost:8801/sendMsg
    *@param: null
    *@Author: SGZ
    *@Date: 2020/10/4
    *@return:
    *
    **/
    @GetMapping("/sendMsg")
    public String sendMsg() {
        return messageProvider.send();
    }
}
```

## 测试

### 启动模块

eureka7001

cloud-stream-rabbitmq-provider8801

### 访问 http://localhost:8801/sendMsg  ，然后查看是否发送成功



# 88_Stream消息驱动之消费者

## 搭建模块

### 创建module

创建名为 cloud-stream-rabbitmq-consumer8802 的module



### pom

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-stream-rabbit</artifactId>
    </dependency>
    <!--eureka client-->
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



### application.yml, 主要注意的是，这里的是配置input d的  spring.cloud.stream.bindings.input

```yaml
server:
  port: 8802


spring:
  application:
    name: cloud-stream-consumer
  cloud:
    stream:
      binders: # 在此处配置要绑定的 rabbitmq 的服务信息
        defaultRabbit: # 表示定义的名称，用户binding 整合
          type: rabbit # 消息组件类型
          environment: # 设置 rabbit mq 的相关环境配置
            spring:
              rabbitmq:
                host: localhost
                port: 5672
                username: guest
                password: guest
      bindings: # 服务的整合处理
        input: # 这个名字是一个通道的名称
          destination: studyExchange # 表示要使用的 Exchange 名称定义
          content-type: application/json # 设置消息类型，本次为json, 文本则设置为 “text/plain”
          binder: defaultRabbit # 设置要绑定的消息服务的具体设置

eureka:
  client:
    fetch-registry: true # 表示是否将自己注册进eureka ， 默认为true
    register-with-eureka: true # 是否从 eurekaServer 抓取已有的注册信息，默认为true, 单节点无所谓，集群必须设置为true才能配合 ribbon 使用负载均衡
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
  instance:
    instance-id: receive-8802.com
    prefer-ip-address: true  # eureka 上显示IP
```



### 主启动类

```java
package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname StreamMQMain8802
 * @Description TODO
 * @Date 2020/10/4 12:53
 * @Created by SGZ
 */
@SpringBootApplication
public class StreamMQMain8802 {
    public static void main(String[] args) {
        SpringApplication.run(StreamMQMain8802.class, args);
    }
}
```



### 消息监听接收类，注意这个不是一个 controller· 类，只是命名带有而已

```java
package com.su.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Classname ReceiveMessageController
 * @Description TODO
 * @Date 2020/10/4 12:54
 * @Created by SGZ
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void input(Message<String>message) {
        System.out.println("消费者 1 号， 接收到的消息：" + message.getPayload() + "\t port:" + serverPort);
    }
}
```



## 测试

先按 87_Stream消息驱动之生产者 的测试步骤启动模块，和访问消息接口，可以看到消息消费者控制台的打印信息：

```
消费者 1 号， 接收到的消息：a9dd26c9-04c5-422f-9ba3-4fc67941def7	 port:8802
消费者 1 号， 接收到的消息：21122f76-2dda-4762-b2b1-2c047ae9f3c8	 port:8802
```



# 89 至 91  消息重复消费和持久化问题解决

## 目前存在的问题

1、消费者集群的情况下，会产生多次消费的问题

2、如果消费者挂了之后，挂了到重启之间产生的消息，没有被消费到



## 如何解决？

由于stream 有分组的概念，默认不配置分组名称的情况下，会生成一个分组名称，默认分组的名称都是不一样的，分组流水号不一样，被认为不同组，可以消息

所以我们需要自定义配置分组，解决重复消费和消息持久化的问题

### 增加配置：

我们在 spring.cloud.stream.bindings.input.binder 下面增加一个 group 的配置，集群之间保持配置的group 相同即可



## 测试

#### 测试是否不再重复消费

新建一个 名为 cloud-stream-rabbitmq-consumer8803 的 module ， 除了主启动类的名称和端口号略有差异，其它一切如 8802

启动 8803和8802后，访问 http://localhost:8801/sendMsg 两次，

查看消息是否均摊到了8802和8803两个服务被消费



#### 测试是否被持久化（消费者异常期间没有被消费的消费。重启之后还能正确消费）

先停掉8802和8803， 然后访问 http://localhost:8801/sendMsg两次

再启动 8802或者8803， 查看控制台是否打印消费的消息

当启动8802之后，从控制台可以看到打印以下消息

```
消费者 1 号， 接收到的消息：e3ef49b8-9204-4b03-8f00-4a8bb35664cf	 port:8802
消费者 1 号， 接收到的消息：645be22d-5768-43da-9c25-9329dd2b5afc	 port:8802
```











