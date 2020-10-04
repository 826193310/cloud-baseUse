# 92 — 94  sleuth 服务搭建



sleuth 的demo和搭建演示就不再重新进行新建module 处理，使用 cloud-consumer-order80  module, 和 cloud-provider-payment8001 这个module 进行处理，



## 启动zipkin

sleuth 需要先下载 zipkin 的服务，springcloud 从F版本开始就不用自己搭建zipkin 服务了，只需调用jar 包即可，我用的是 zipkin-server-2.12.9-exec.jar  ，下载jar 包之后， java -jar zipkin-server-2.12.9-exec.jar  



## 业务搭建

### pom

8001和 order80 增加以下的pom

```xml
<!--包含了 sleuth 和 zipkin-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
</dependency>
```



### application.yml 增加配置

8001和 order80 的 application.yml 增加以下配置

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411 # 跟踪信息汇总到 zipkin 的地址
  sleuth:
    sampler:
      probability: 1 # 采样率值介于 0 到 1之间， 1 则表示全部采集
```





order80 的 OrderController 增加以下代码，都较为简单不再过多描述

```java
@GetMapping("/consumer/payment/zipkin")
public String paymentZipkin() {
    return restTemplate.getForObject(PAYMENT_URL + "/payment/zipkin", String.class);
}
```



payment8001 增加以下代码,以响应服务

```java
@GetMapping(value = "/payment/zipkin")
public String paymentZipkin() {
    return "HI I AM PAYMENT ZIPKIN SERVER FALL BACK";
}
```

 



## 测试

分别启动eureka7001,  payment8001 , order80 ， 此处有个坑，注意的是 payment 8001 是要连接数据库的，事先需要准备好数据库的连接处理



访问 http://localhost/consumer/payment/zipkin 多几次



访问 zipkin 界面，http://localhost:9411/zipkin/ 点击查找， 可以看到相关服务的调用情况，点进去可以看到调用的路径等信息

