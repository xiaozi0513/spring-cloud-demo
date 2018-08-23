package com.test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 * Created by chencc on 2018/4/26.
 */
public class CompletableBuilder {
    /**
     * 主要是为了增加
     * @param supplier
     * @param executor
     * @param <T>
     * @return
     */
    public static <T> CompletableFuture<T> buildAsyCompletable(Supplier<T> supplier, Executor executor) {

        CompletableFuture<T> future = CompletableFuture.supplyAsync(()->{
            T res= supplier.get();
            return res;

        }, executor);

        return future;
    }
}
