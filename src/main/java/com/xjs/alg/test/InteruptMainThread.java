package com.xjs.alg.test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class InteruptMainThread {

    public static void main(String[] args) throws InterruptedException {
//        final Thread thread = Thread.currentThread();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Thread[] threads = new Thread[3];

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            try {
//                Runtime.getRuntime().exec("exit");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            Runtime.getRuntime().exit(1);
//            thread.interrupt();
            if (!executorService.isShutdown())
                executorService.shutdownNow();
            if (executorService.isShutdown() && !scheduledExecutorService.isShutdown())
                scheduledExecutorService.shutdownNow();
//            for (Thread each : threads) {
//                if (!each.isInterrupted())
//                    each.interrupt();
//            }
//
//            if (!scheduledExecutorService.isShutdown())
//                scheduledExecutorService.shutdownNow();

        }, 4, 1, TimeUnit.SECONDS);

        executorService.submit(() -> {
            for (;; ) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("hello world");
                } catch (InterruptedException e) {
                    throw new InterruptedException();
                }
            }
        });

//        for (int i = 0; i < 3; i++) {
//            threads[i] = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    for (;; ) {
//                        try {
//                            TimeUnit.SECONDS.sleep(2);
//                            System.out.println(Thread.currentThread().getName() + "hello world");
//                        } catch (InterruptedException e) {
//                            // ignored
//                        }
//                    }
//                }
//            });
//            threads[i].start();
//        }

//        thread.join();
//        executorService.shutdownNow();

    }
}
