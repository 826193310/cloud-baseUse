package com.su.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname StorageDao
 * @Description TODO
 * @Date 2020/11/30 22:13
 * @Created by SGZ
 */
@Mapper
public interface StorageDao {

    // 扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
