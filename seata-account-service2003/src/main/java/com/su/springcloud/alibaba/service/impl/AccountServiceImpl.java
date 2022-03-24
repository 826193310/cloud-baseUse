package com.su.springcloud.alibaba.service.impl;

import com.su.springcloud.alibaba.dao.AccountDao;
import com.su.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Classname AccountServiceImpl
 * @Description TODO
 * @Date 2020/11/30 23:09
 * @Created by SGZ
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        System.out.println("从 accountService 中扣减余额开始");
        accountDao.decrease(userId, money);
        // 模拟扣款出现异常
        /*int a = 10/0;*/
        System.out.println("从 accountService 中扣减余额结束");
    }
}
