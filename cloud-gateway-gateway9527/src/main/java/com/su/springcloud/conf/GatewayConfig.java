package com.su.springcloud.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname GatewayConfig
 * @Description gateway config 类
 * @Date 2020/9/5 16:32
 * @Created by SGZ
 */
@Configuration
public class GatewayConfig {

    /**
    *
    *@Description: 配置 gateway 路由，当访问 http://localhost:9527/guonei 的时候，跳转到百度的 http://news.baidu.com/guonei
    *@param: null
    *@Author: SGZ
    *@Date: 2020/9/5
    *@return:
    *
    **/
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        routes.route("path_route_su1", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return routes.build();
    }
}
