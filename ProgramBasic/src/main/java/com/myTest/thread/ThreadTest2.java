package com.myTest.thread;

public class ThreadTest2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(Thread.currentThread().getName() + "------------------");
		}
	}

}
