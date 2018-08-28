package com.wh.landing;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCommand;
import com.netflix.hystrix.util.HystrixRollingNumberEvent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2018/8/27 下午5:47
 */
public class HystrixMetricsStoredPublisherCommand implements HystrixMetricsPublisherCommand {

    private final HystrixCommandKey commandKey;
    private final HystrixCommandGroupKey commandGroupKey;
    private final HystrixCommandMetrics metrics;
    private final HystrixCircuitBreaker circuitBreaker;
    private final HystrixCommandProperties properties;

    public HystrixMetricsStoredPublisherCommand(HystrixCommandKey commandKey, HystrixCommandGroupKey commandGroupKey, HystrixCommandMetrics metrics, HystrixCircuitBreaker circuitBreaker, HystrixCommandProperties properties) {
        this.commandKey = commandKey;
        this.commandGroupKey = commandGroupKey;
        this.metrics = metrics;
        this.circuitBreaker = circuitBreaker;
        this.properties = properties;
    }

    @Override
    public void initialize() {
        System.out.println("HystrixMetricsStoredPublisherCommand ----------- ");
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(
                () -> {
                    StringBuilder sb = new StringBuilder(new Date() + "hystrix-metrics ------------------ ");
                    sb.append("\r\n");
                    sb.append("name: " + commandKey.name() + "\r\n");
                    sb.append("group: " + metrics.getCommandGroup().name() + "\r\n");
                    sb.append("isCircuitBreakerOpen: " + circuitBreaker.isOpen() + "\r\n");
                    sb.append("rollingCountSuccess: " + metrics.getRollingCount(HystrixEventType.SUCCESS) + "\r\n");
                    sb.append("Success: " + metrics.getRollingCount(HystrixRollingNumberEvent.SUCCESS) + "\r\n");
                    sb.append("rollingCountBadRequests: " + metrics.getRollingCount(HystrixRollingNumberEvent.BAD_REQUEST) + "\r\n");
                    sb.append("rollingCountTimeout: " + metrics.getRollingCount(HystrixEventType.TIMEOUT) + "\r\n");
                    sb.append("executionTimeMean: " + metrics.getExecutionTimeMean() + "\r\n");


                    // 在开启的线程中，定时读取 threadPoolKey、metrics、properties 三个字段的属性值
                    // 写入 mq 或者 db 供后续数据统计分析
                        System.out.println(sb.toString());
                }, 0, 5, TimeUnit.SECONDS);
    }
}
