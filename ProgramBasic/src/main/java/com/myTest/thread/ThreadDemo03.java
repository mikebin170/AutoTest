package com.myTest.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThreadDemo03 {
    public static void main(String[] args) {
        Callable<Object> callable=new Ticket<Object>();
        FutureTask<Object> futureTask=new FutureTask<Object>(callable);
        Thread t = new Thread(futureTask);
//        System.out.println(Thread.currentThread().getName());
        t.start();

    }

    static class Ticket<Object> implements Callable<Object>{


        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "我是通过实现Callable接口通过FutureTask包装器来实现的线程");
            return null;
        }
    }
}
