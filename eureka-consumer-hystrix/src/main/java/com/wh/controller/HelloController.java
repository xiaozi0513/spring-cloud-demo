package com.wh.controller;

import com.wh.remote.HelloRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wang_hui
 * @date: 2018/7/24 下午5:51
 * @since:
 */
@RestController
@RequestMapping("/consumer/hystrix/demo")
public class HelloController {

    @Autowired
    private HelloRemote helloRemote;

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
        return "receive info from consumer: " + helloRemote.hello(name);
    }

    @RequestMapping("/age")
    public Integer age(@RequestParam("age") Integer age) {
        return helloRemote.getAge(age);
    }

    @RequestMapping("/hystrix")
    public void hystrix(@RequestParam("age") Integer age) {
        for (int i = 0; i < 50; i++) {
            helloRemote.getAge(age);
        }
    }

}
