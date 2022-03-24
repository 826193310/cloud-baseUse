package com.su.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @Classname AccountService
 * @Description TODO
 * @Date 2020/11/30 23:07
 * @Created by SGZ
 */
public interface AccountService {

    void decrease(Long userId, BigDecimal money);
}
