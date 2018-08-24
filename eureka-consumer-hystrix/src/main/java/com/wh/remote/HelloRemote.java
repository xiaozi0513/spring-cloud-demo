package com.wh.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wang_hui
 * @date: 2018/7/24 下午5:48
 * @since:
 */
@FeignClient(name = "eureka-producer", fallbackFactory = HelloRemoteHystrix.class)
public interface HelloRemote {

    @RequestMapping(value = "/demo/hello", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);

    @RequestMapping(value = "/demo/age", method = RequestMethod.GET)
    Integer getAge(@RequestParam("age") Integer age);

}
