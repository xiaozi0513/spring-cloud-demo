package com.wh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2018/8/28 下午5:58
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${neo.hello}")
    private String neo_hello;

    @GetMapping("/hello")
    public String hello() {
        return this.neo_hello;
    }


}
