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
会交替出现 8001 端口和 8002 端口