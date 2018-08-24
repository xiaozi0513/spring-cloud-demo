package com.wh.remote;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: wang_hui
 * @date: 2018/8/9 上午11:34
 * @since:
 */
@Slf4j
@Component
public class HelloRemoteHystrix implements FallbackFactory<HelloRemote> {
//    @Override
//    public String hello(@RequestParam("name") String name) {
//        return "default value";
//    }
//
//    @Override
//    public Integer getAge(@RequestParam("age") Integer age) {
//        return 0;
//    }

    @Override
    public HelloRemote create(Throwable throwable) {
        return new HelloRemote() {
            @Override
            public String hello(@RequestParam("name") String name) {
                log.error("===>call method of HelloRemote error.", throwable);
                return "default value";
            }

            @Override
            public Integer getAge(@RequestParam("age") Integer age) {
                log.error("===>call method of HelloRemote error.", throwable);
                return 0;
            }
        };
    }
}
