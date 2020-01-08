package com.myTest.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest1 {

	public static void main(String[] args) {
		//线程池池子大小 最大 65536，适合短任务
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	    //固定大小
		//ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
		//单个
		//ExecutorService cachedThreadPool = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 100; i++) {
			final int index = i;
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + "   "+index);
				}
			});
		}
		
		cachedThreadPool.shutdown();
		//替代 timer 的线程池
//		 ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//		 
//		 scheduledThreadPool.schedule(new Runnable() {
//			public void run() {
//				System.out.println("111111111");
//			}
//		}, 1, TimeUnit.MINUTES);
		
	}

}
