package com.myTest.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Task implements Callable<Long> {

	private long begin;
	private long end;
	
	public Task(long begin, long end) {
		super();
		this.begin = begin;
		this.end = end;
	}


	@Override
	public Long call() throws Exception {
		long sum = 0;
		Thread.sleep(4000);
		for (long i = begin+1; i <= end; i++) {
			sum += i;
		}
		System.out.println(Thread.currentThread().getName()+"________"+sum);
		
		return sum;
	}

}

public class TestCallabel {

	public static void main(String[] args) {

		int allcount = 100;
		int pagesize = 1;

		int page = allcount % pagesize == 0 ? allcount / pagesize : (allcount / pagesize) + 1;

		System.out.println(page);
		List<Future<Long>> futures = new ArrayList<Future<Long>>();
		ExecutorService cachedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < page; i++) {
			long begin = i * pagesize;
			long end = (i + 1) * pagesize;
			if (end > allcount) {
				end = allcount;
			}
           System.out.println(begin + " "+end);
           Future<Long>  future = cachedThreadPool.submit(new Task(begin,end));
           futures.add(future);
		}

		long sum = 0;
		for (Future<Long> future : futures) {
				try {
//					sum+=future.get(3,TimeUnit.SECONDS);
					sum=future.get();
				} catch (InterruptedException |ExecutionException  e) {
					//e.printStackTrace();
					System.err.println(future);
					e.printStackTrace();
				}
		}
		
	System.out.println(sum);

	}

}
