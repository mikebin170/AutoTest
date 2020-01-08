package com.myTest.thread;

public class ThreadTest1 extends Thread{
	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println(Thread.currentThread().getName() + "------------------");
		}
	}
}
