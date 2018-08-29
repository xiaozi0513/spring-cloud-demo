package com.wh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2018/8/29 下午7:47
 */
@RestController
@RefreshScope
public class HellloController {

    @Value("${neo.hello}")
    private String hello;

    @GetMapping("/hello")
    public String hello() {
        return hello;
    }

}
