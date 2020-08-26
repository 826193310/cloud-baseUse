package com.su.springcloud.service;

import com.su.springcloud.configuration.FeignConfiguration;
import com.su.springcloud.entities.CommonResult;
import com.su.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Classname JuejinFeignService
 * @Description TODO
 * @Date 2020/8/15 11:38
 * @Created by SGZ
 */
@Component
@FeignClient( value = "http://juejin.hlzq.com", url = "http://juejin.hlzq.com:8090", configuration = FeignConfiguration.class)
public interface JuejinFeignService {


    @GetMapping(value = "/hlzqjj/api/hljjapnewsinfos/index")
    String index();

    @GetMapping(value = "/hlzqjj/api/hljjapnewsinfos/index")
    ResponseEntity<byte[]> index2();
}
