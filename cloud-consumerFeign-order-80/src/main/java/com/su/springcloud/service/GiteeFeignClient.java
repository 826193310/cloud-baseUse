package com.su.springcloud.service;

import com.su.springcloud.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname GiteeFeignClient
 * @Description TODO
 * @Date 2020/8/17 17:44
 * @Created by SGZ
 */

@FeignClient(name = "github-client", url = "https://api.github.com", configuration = FeignConfiguration.class)
public interface GiteeFeignClient {

    @RequestMapping(value = "/search/repositories", method = RequestMethod.GET)
    String searchRepo(@RequestParam("q") String queryStr);

}
