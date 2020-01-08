package com.myTest.timer;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask{
	
	private String name;
	

	public MyTimerTask(String name) {
		super();
		this.name = name;
	}



	@Override
	public void run() {
		System.out.println(name+" run"+ Thread.currentThread().getName());
	}

}
