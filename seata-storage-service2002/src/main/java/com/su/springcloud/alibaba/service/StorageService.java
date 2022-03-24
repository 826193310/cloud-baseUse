package com.su.springcloud.alibaba.service;

/**
 * @Classname StorageService
 * @Description TODO
 * @Date 2020/11/30 22:24
 * @Created by SGZ
 */
public interface StorageService {

    // 扣减库存
    void decreate(Long productId, Integer count);
}
