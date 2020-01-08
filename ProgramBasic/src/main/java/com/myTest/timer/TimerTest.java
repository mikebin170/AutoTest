package com.myTest.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	
	public static void main(String[] args) {
		Timer timer = new Timer();
		
		timer.schedule(new MyTimerTask(""+System.currentTimeMillis()), 0l, 3000l);

		timer.scheduleAtFixedRate(new TimerTask() {
		public void run() {
			try {
				  System.out.println("11111!"+ this.scheduledExecutionTime());
				Thread.sleep(4*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}, 0, 3* 1000);
	
//      timer.schedule(new TimerTask() {
//		public void run() {
//			try {
//				  System.out.println("22222!"+ this.scheduledExecutionTime());  
//				Thread.sleep(4*1000);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}, 0l, 3* 1000);
		
	}

}

