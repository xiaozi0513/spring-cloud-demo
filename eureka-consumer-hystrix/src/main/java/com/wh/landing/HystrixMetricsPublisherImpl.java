package com.wh.landing;

import com.netflix.hystrix.*;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCollapser;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherCommand;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisherThreadPool;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: wang_hui
 * @date: 2018/8/27 下午5:39
 */
public class HystrixMetricsPublisherImpl extends HystrixMetricsPublisher {

    @Override
    public HystrixMetricsPublisherCommand getMetricsPublisherForCommand(HystrixCommandKey commandKey, HystrixCommandGroupKey commandGroupKey, HystrixCommandMetrics metrics, HystrixCircuitBreaker circuitBreaker, HystrixCommandProperties properties) {
        return new HystrixMetricsStoredPublisherCommand(commandKey, commandGroupKey, metrics, circuitBreaker, properties);
    }

    @Override
    public HystrixMetricsPublisherThreadPool getMetricsPublisherForThreadPool(HystrixThreadPoolKey threadPoolKey, HystrixThreadPoolMetrics metrics, HystrixThreadPoolProperties properties) {
        return new HystrixMetricsStoredPublisherThreadPool(threadPoolKey, metrics, properties);
    }

    @Override
    public HystrixMetricsPublisherCollapser getMetricsPublisherForCollapser(HystrixCollapserKey collapserKey, HystrixCollapserMetrics metrics, HystrixCollapserProperties properties) {
        return super.getMetricsPublisherForCollapser(collapserKey, metrics, properties);
    }

    static class HystrixMetricsStoredPublisherThreadPool implements HystrixMetricsPublisherThreadPool {

        private final HystrixThreadPoolKey threadPoolKey;
        private final HystrixThreadPoolMetrics metrics;
        private final HystrixThreadPoolProperties properties;

        public HystrixMetricsStoredPublisherThreadPool(
                HystrixThreadPoolKey threadPoolKey,
                HystrixThreadPoolMetrics metrics,
                HystrixThreadPoolProperties properties) {
            this.threadPoolKey = threadPoolKey;
            this.metrics = metrics;
            this.properties = properties;
        }

        /**
         * 只会在注册的时候执行一次，所以需要我们自己开启线程定时监控 构造函数传入的这几个对象的状态
         */
        @Override
        public void initialize() {
            System.out.println("HystrixMetricsStoredPublisherThreadPool ----------- ");
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(
                    () -> {
                        StringBuilder sb = new StringBuilder(new Date() + "hystrix-metrics ------------------ ");
                        sb.append("\r\n");
                        sb.append("CurrentQueueSize: " + metrics.getCurrentQueueSize());
                        sb.append("CurrentCorePoolSize: " + metrics.getCurrentCorePoolSize());
                        sb.append("CumulativeCountThreadsRejected: " + metrics.getCumulativeCountThreadsRejected());
                        sb.append("CurrentActiveCount: " + metrics.getCurrentActiveCount());

                        // 在开启的线程中，定时读取 threadPoolKey、metrics、properties 三个字段的属性值
                        // 写入 mq 或者 db 供后续数据统计分析
                        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                            System.out.println(sb.toString());
                        }, 0, 2, TimeUnit.SECONDS);
                    });
        }
    }
}



