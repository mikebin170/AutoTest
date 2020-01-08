package com.myTest.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadSubmitDemo
{

    class ThreadDemo implements Runnable
    {

        public void run()
        {
        	for (int i = 0; i < 200; i++) {
        		//http
                System.err.println("hello word");
			}
        	
        }

    }

    public static void main(String[] args)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        ThreadSubmitDemo threadPoolDemo = new ThreadSubmitDemo();
        for (int i = 0; i < 5; i++)
        {
            ThreadDemo threadDemo = threadPoolDemo.new ThreadDemo();
            Future future = executorService.submit(threadDemo);

            // 可以取消执行
//            future.cancel(true);

            // 可以获取返回结果，如果future.get()==null 且无异常，表示执行成功
            try
            {
                if (future.get(1,TimeUnit.SECONDS) == null) System.out.println("执行完成");
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        executorService.shutdown();
    }
}
