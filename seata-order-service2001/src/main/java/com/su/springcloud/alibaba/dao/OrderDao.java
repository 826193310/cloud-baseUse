package com.su.springcloud.alibaba.dao;

import com.su.springcloud.alibaba.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname OrderDao
 * @Description TODO
 * @Date 2020/11/30 20:07
 * @Created by SGZ
 */
@Mapper
public interface OrderDao {

    // 1 创建订单
    void create(Order order);

    // 2 修改订单状态从 0 改为 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
