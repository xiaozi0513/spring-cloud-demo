package com.wh;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.wh.landing.HystrixMetricsPublisherImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: wang_hui
 * @date: 2018/7/24 下午5:45
 * @since:
 */
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerHystrixApplication.class, args);
    }

    static {
        HystrixPlugins.getInstance().registerMetricsPublisher(new HystrixMetricsPublisherImpl());
    }

}
