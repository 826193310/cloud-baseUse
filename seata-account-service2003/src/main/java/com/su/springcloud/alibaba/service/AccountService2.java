package com.su.springcloud.alibaba.service;

import com.su.springcloud.alibaba.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @Classname AccountService2
 * @Description TODO
 * @Date 2020/12/1 10:36
 * @Created by SGZ
 */
@Service
public class AccountService2 {

    @Autowired
    private AccountDao accountDao;

    @Transactional
    public void decrease(Long userId, BigDecimal money) {
        System.out.println("从 accountService 中扣减余额开始");
        accountDao.decrease(userId, money);
        // 模拟扣款出现异常
        int a = 10/0;
        System.out.println("从 accountService 中扣减余额结束");
    }
}
