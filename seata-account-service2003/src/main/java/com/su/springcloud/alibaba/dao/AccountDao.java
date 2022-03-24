package com.su.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Classname AccountDao
 * @Description TODO
 * @Date 2020/11/30 23:02
 * @Created by SGZ
 */
@Mapper
public interface AccountDao {

    /**
    *
    *@Description: 扣减账户余额
    *@param: null
    *@Author: SGZ
    *@Date: 2020/11/30
    *@return:
    *
    **/
    void decrease(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
