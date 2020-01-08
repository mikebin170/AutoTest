package com.myTest.thread;


public class ThreadTest {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 100; i++) {
			ThreadTest1 myThread1 = new ThreadTest1();
			 myThread1.start();
		}

//		for (int i = 0; i < 100; i++) {
//			ThreadTest2 myThread2 = new ThreadTest2();
//			Thread t = new Thread(myThread2);
//			t.start();
//		}
	}
}
