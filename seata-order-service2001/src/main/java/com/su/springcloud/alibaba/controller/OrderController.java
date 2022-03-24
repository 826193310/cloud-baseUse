package com.su.springcloud.alibaba.controller;

import com.su.springcloud.alibaba.entity.CommonResult;
import com.su.springcloud.alibaba.entity.Order;
import com.su.springcloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname OrderController
 * @Description TODO
 * @Date 2020/11/30 20:56
 * @Created by SGZ
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
    *
    *@Description: http://localhost:2001/order/create?userId=1&productId=1&count=10&money=100
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/30
    *@return:
    *
    **/
    @GetMapping("/order/create")
    public CommonResult order(Order order) {
        orderService.create(order);
        return new CommonResult(200, "success");
    }
}
