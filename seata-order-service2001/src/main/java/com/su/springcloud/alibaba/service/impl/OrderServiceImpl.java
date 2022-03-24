package com.su.springcloud.alibaba.service.impl;

import com.su.springcloud.alibaba.dao.OrderDao;
import com.su.springcloud.alibaba.entity.Order;
import com.su.springcloud.alibaba.service.AccountService;
import com.su.springcloud.alibaba.service.OrderService;
import com.su.springcloud.alibaba.service.StorageService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname OrderServiceImpl
 * @Description TODO
 * @Date 2020/11/30 20:29
 * @Created by SGZ
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void create(Order order) {
        System.out.println("-----开始新建订单");
        orderDao.create(order);

        System.out.println("------订单微服务开始调用库存扣减");
        storageService.decrease(order.getProductId(), order.getCount());
        System.out.println("------订单微服务结束调用库存扣减");

        System.out.println("------订单微服务开始调用账户扣减");
        accountService.decrease(order.getUserId(), order.getMoney());
        System.out.println("------订单微服务结束调用账户扣减");

        // 修改订单的状态， 从 0 到 1， 1代表已经完成
        System.out.println("修改订单状态开始");
        orderDao.update(order.getUserId(), 0);
        System.out.println("修改订单状态结束");

        System.out.println("下订单结束");
    }
}
