package com.myTest.pool;
//用空间来解决线程安全问题,线程共享sn
//通过synchronized资源锁来解决线程安全问题
public class TestThreadLocal {  
    // ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值    本質map
    private ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {  
        public Integer initialValue() {  
            return 0;  
        }  
    };  
    
    // ②获取下一个序列值  
    public int getNextNum() {  
        seqNum.set(seqNum.get() + 1);  
        return seqNum.get();  
    }  
  
    public static void main(String[] args) {  
        TestThreadLocal sn = new TestThreadLocal();  
        // ③ 3个线程共享sn，各自产生序列号  
        TestClient t1 = new TestClient(sn,1);
        TestClient t2 = new TestClient(sn,2);
        TestClient t3 = new TestClient(sn,3);
        t1.start();  
        t2.start();  
        t3.start();  
    }  
}  

 class TestClient extends Thread {  
    private TestThreadLocal sn;  
    private int runtime;
    public TestClient(TestThreadLocal sn,int runtime) {

        this.sn = sn;
        this.runtime=runtime;
    }  

    public void run() {  
        for (int i = 0; i < runtime; i++) {
            // ④每个线程打出3个序列值  
            System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["  
                     + sn.getNextNum() + "]");  
        }  
    }  
}  

