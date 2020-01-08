package com.myTest.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TestCountDownLunch {

	 public static void main(String[] args) throws InterruptedException {
	        //new CountDownLatch(0)当为0时，latch.await()无效果
	        CountDownLatch latch = new CountDownLatch(10);
	        for (int i = 0; i <10; i++) {
	        	new Thread() {
		            @Override
		            public void run() {
		                try {
		                    Thread.sleep(1000);
		                    latch.countDown();
		                } catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }.start();
			}
	        
	        /**
	         * 最多等待10秒
	         * 1.如果10秒内，没有countDown为0，10秒后发行
	         * 2.如果10秒内，countDown为0，立刻发行，不用等待10秒
	         */
	      //  latch.await(10,TimeUnit.SECONDS);
	        latch.await();
	        System.out.println("==========");
	    }
}
