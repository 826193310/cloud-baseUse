package com.su.springcloud.dao;

import com.su.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname PaymentDao
 * @Description TODO
 * @Date 2020/7/29 23:55
 * @Created by SGZ
 */
@Mapper
public interface PaymentDao {
    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);

}
