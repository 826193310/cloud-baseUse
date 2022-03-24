package com.su.springcloud.alibaba.service.impl;

import com.su.springcloud.alibaba.dao.StorageDao;
import com.su.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Classname StorageServiceImpl
 * @Description TODO
 * @Date 2020/11/30 22:26
 * @Created by SGZ
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Override
    public void decreate(Long productId, Integer count) {
        System.out.println("storageService 扣减库存开始");
        storageDao.decrease(productId, count);
        System.out.println("storageService 扣减库存结束");
    }
}
