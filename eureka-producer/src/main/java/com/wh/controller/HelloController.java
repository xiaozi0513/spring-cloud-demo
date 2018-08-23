package com.wh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author: wang_hui
 * @date: 2018/7/13 下午3:19
 * @since:
 */
@RestController
@RequestMapping("/demo")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(@RequestParam("name") String name) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int a = 1/0;
        return "hello, " + name + " - " + new Date();
    }

    @RequestMapping("/age")
    public Integer timeout(@RequestParam("age") Integer age) {
        if (age < 10) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (age < 20) {
            int a = 1 / 0;
        }
        return age;
    }

}
