package com.myTest.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadDemo05 {
    private static int POOL_NUM = 10;     //线程池数量
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(5);
//        RunnableThread runnableThread =new RunnableThread();
//        executorService.execute(runnableThread);

        for (int i=0;i<POOL_NUM;i++){
            RunnableThread runnableThread =new RunnableThread();
            executorService.execute(runnableThread);
            System.out.println(Thread.currentThread().getName()+"正在被执行");
        }
        executorService.shutdown();
    }

static class RunnableThread implements Runnable{

    @Override
    public void run() {
        System.out.println("通过线程池方式创建的线程：" + Thread.currentThread().getName() + " ");
    }
}

}
