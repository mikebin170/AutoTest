package com.myTest.pool;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {

	//通过连个线程lock 看cpu调度情况
	public static void main(String[] args) {
		
		AtomicInteger atomicInteger = new AtomicInteger();
		
		atomicInteger.incrementAndGet();
		
		
		
		
		Lock lock = new ReentrantLock();

		new Thread() {
			@Override
			public void run() {
			
				String tName = Thread.currentThread().getName();
				try {
					// 获取不到锁，就等7秒，如果7秒后还是获取不到就返回false
					if (lock.tryLock(7000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "获取到锁！");
					} else {
						System.out.println(tName + "获取不到锁！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					for (int i = 0; i < 10; i++) {
						System.out.println(tName + ":" + i);
					}

				} catch (Exception e) {
					System.out.println(tName + "出错了！！！");
				} finally {
					System.out.println(tName + "释放锁！！");
				    lock.unlock();
				}
			}
		}.start();

		
		new Thread() {
			@Override
			public void run() {
				String tName = Thread.currentThread().getName();
				try {
					// 获取不到锁，就等7秒，如果7秒后还是获取不到就返回false
					if (lock.tryLock(7000, TimeUnit.MILLISECONDS)) {
						System.out.println(tName + "获取到锁！");
					} else {
						System.out.println(tName + "获取不到锁！");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					for (int i = 0; i < 10; i++) {
						System.out.println(tName + ":" + i);
					}

				} catch (Exception e) {
					System.out.println(tName + "出错了！！！");
				} finally {
					System.out.println(tName + "释放锁！！");
					lock.unlock();
				}
			}
		}.start();
	}

}
