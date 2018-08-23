package com.wh.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wang_hui
 * @date: 2018/8/9 上午11:34
 * @since:
 */
@Component
public class HelloRemoteHystrix implements HelloRemote {
    @Override
    public String hello(@RequestParam("name") String name) {
        return "default value";
    }

    @Override
    public Integer getAge(@RequestParam("age") Integer age) {
        return 0;
    }
}
