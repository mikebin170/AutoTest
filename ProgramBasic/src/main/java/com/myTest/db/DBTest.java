package com.myTest.db;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DBTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        int reulst = AddDataTo.insert("12311","test12211","test111");


        for (int i = 0; i < 10; i++) {
            String uid = UUID.randomUUID().toString();
            int reulst = AddDataTo.insert(uid, "testfan" + i, "test" + i);
//            System.out.println("reulst " + i);
//
//            System.out.println(reulst);
        }


        // int reulst = AddDataToDB.insert("12311","test12211","test111");
//        int allcount =1000;
//        int pagesize =10;
//
//        int allpage = allcount%pagesize==0?allcount/pagesize:(allcount/pagesize)+1;
//        System.out.println(allpage);
//        for (int i = 0; i < allcount; i++) {
//            int begin = i*pagesize;
//            int end = (i+1)*pagesize;
//            if (allcount-end<pagesize){
//                    end = allcount;
//            }
//            System.out.println(i +" begin "+begin+ " end " + end);
//        }


//        int allcount =100000;
//        int pagesize =100;
//        int allpage = allcount%pagesize==0?allcount/pagesize:(allcount/pagesize)+1;
//        for (int i = 0; i < allpage; i++) {
//            int begin = i*pagesize;
//            int end = (i+1)*pagesize;
//            if(end>allcount) {
//                end = 	allcount;
//            }
//            //System.out.println(" begin "+begin + " end " +end);
//            new AddDataTo.DbThread(begin, end).start();
////            cachedThreadPool.execute(new AddDataToDB.DbThread(begin, end));
//        }
    }
}