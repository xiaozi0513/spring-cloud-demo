package com.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: wang_hui
 * @date: 2018/8/8 下午3:40
 * @since:
 */
public class Test {

    public static void main(String[] args) {
        List<CompletableFuture> list = new ArrayList<>();

        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date() + " task1 start");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " task1 doing ...");
            return "result1";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(new Date() + " task2 start");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new Date() + " task2 doing ...");
            return "result2";
        });

        list.add(FutureResultUtil.within(completableFuture1).exceptionally(e -> FutureResultUtil.defaultValue("exception1", e, "completableFuture1 error.")));
        list.add(FutureResultUtil.within(completableFuture2).exceptionally(e -> FutureResultUtil.defaultValue("exception2", e, "completableFuture2 error.")));

        CompletableFuture<Object> anyof = CompletableFuture.anyOf(list.toArray(new CompletableFuture[list.size()]));
        try {
            System.out.println(new Date() + " first done task: " + anyof.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        CompletableFuture allof = CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]));
        allof.join();
        try {
            System.out.println(new Date() + " all done task: " + allof.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            String r1 = completableFuture1.get();
            String r2 = completableFuture2.get();
            System.out.println(new Date() + " r1=" + r1);
            System.out.println(new Date() + " r2=" + r2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
