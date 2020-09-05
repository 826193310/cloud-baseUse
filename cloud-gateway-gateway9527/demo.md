添加完成服务网关之后，原先访问payment
 http://localhost:8001/payment/get/1

现在可以改成 http://localhost:9527/payment/get/1

动态路由测试\
启动 EUREKA 7001\
启动 payment 8001\
启动 payment 8002\
启动网关 9527\
访问网关 http://localhost:9527/payment/lb
结果会负载均衡到 payment8001 和 payment8002 上面去
会交替出现 8001 端口和 8002 端口\

**72_GateWay常用的Predicate**\
-
通过 ZoneDateTimeDemo 获取时区  
2020-09-05T19:25:06.809+08:00[GMT+08:00]\
配置文件 predicates 下增加
  -After=2020-09-05T19:25:06.809+08:00[GMT+08:00]\
当前时间 2020-09-05 19：36 时间在配置的时间之后，此时访问http://localhost:9527/payment/lb没有问题\  
把After的时间改为以下时间
After=2020-09-05T20:25:06.809+08:00[GMT+08:00]\
当前时间 2020-09-05 19：36 时间在配置的时间之前\
此时访问 http://localhost:9527/payment/lb 报错，因为当前时间在配置的时间之前\
Before 和 between 两个 Predicate 也是一致的，都是根据时间区间进行处理\

常用的 Route Predicate 可以参考 https://cloud.spring.io/spring-cloud-static/Hoxton.SR1/reference/htmlsingle/#gateway-request-predicates-factories

**73_GateWay的Filter**\
-
- 自定义Filter\
增加 MyLogGatewayFilter 类，作为 Gateway 的 Filter
注意： 测试完成后，我屏蔽了 MyLogGatewayFilter 中的代码，避免影响其它demo的测试
，当需要使用的时候请放开\
当查询的请求中不带有 uname的时候，判断为非法进入
启动 eureka 7001, payment8001, payment8002, gateway9527
访问 http://localhost:9527/payment/lb?uname=11，正常显示
访问 http://localhost:9527/payment/lb 访问异常，因为没有带有参数 uname









