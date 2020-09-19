## 在github 创建仓库 springcloud-config

github 地址

https://github.com/826193310/springcloud-config



## 克隆仓库到本地

git clone git@github.com:826193310/springcloud-config.git



## 在仓库根目录下创建三个环境变量，进行提交

### config-dev.yml

内容为：

config:
  info: master branch, springcloud-config/config-dev.yml version=1



### config-prod.yml

内容为：

config:
  info: master branch, springcloud-config/config-prod.yml version=1



### config-test.yml

内容为：

config:
  info: master branch, springcloud-config/config-test.yml version=1



## 从master 新建dev 分支

git checkout -b dev

git push origin dev



## 增加 module  cloud-config-center-3344

module 名称为 cloud-config-center-3344

### pom

```java
 <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
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



### application.yml

```yml
server:
  port: 3344
spring:
  application:
    name: cloud-config-center # 应用名称
  cloud:
    config:
      server:
        git:
          uri: git@github.com:826193310/springcloud-config.git
          search-paths: # 搜索目录
            - springcloud-config
      label: master

eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/
```

### 主启动类

```java
package com.su.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Classname ConfigCenterMain3344
 * @Description springcloud-configServer
 * @Date 2020/9/19 12:22
 * @Created by SGZ
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}

```





## 启动测试

### 1、 启动 eureka 7001

### 2、 启动 config-center-3344

### 3、访问  http://localhost:3344/master/config-dev.yml   发现出现报错

页面显示：

```java
This application has no explicit mapping for /error, so you are seeing this as a fallback.

Sat Sep 19 13:51:17 GMT+08:00 2020
There was an unexpected error (type=Not Found, status=404).
Cannot clone or checkout repository: git@github.com:826193310/springcloud-config.git
org.springframework.cloud.config.server.environment.NoSuchRepositoryException: Cannot clone or checkout repository: git@github.com:826193310/springcloud-config.git
	at org.springframework.cloud.config.server.environment.JGitEnvironmentRepository.refresh(JGitEnvironmentRepository.java:296)
```

后台报错：

```java
org.eclipse.jgit.api.errors.TransportException: git@github.com:826193310/springcloud-config.git: invalid privatekey: [B@2aae7e9e
	at org.eclipse.jgit.api.FetchCommand.call(FetchCommand.java:254) ~[org.eclipse.jgit-5.1.3.201810200350-r.jar:5.1.3.201810200350-r]
	at org.eclipse.jgit.api.CloneCommand.fetch(CloneCommand.java:306) ~[org.eclipse.jgit-5.1.3.201810200350-r.jar:5.1.3.201810200350-r]
	at org.eclipse.jgit.api.CloneCommand.call(CloneCommand.java:200) ~[org.eclipse.jgit-5.1.3.201810200350-r.jar:5.1.3.201810200350-r]
	
```

百度了一下，是因为使用 SSH 的问题导致， 修改 application.yml 中的 spring.cloud.config.server.git.uri 的值由 git@github.com:826193310/springcloud-config.git 改成 https://github.com/826193310/springcloud-config.git

### 4、重新启动

重新访问 http://localhost:3344/master/config-dev.yml

出现以下结果：

```
config:
  info: master branch, springcloud-config/config-dev.yml version=1
```

发现已经可以从 github 拉取成功配置