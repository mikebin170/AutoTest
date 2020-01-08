package com.myTest.hello;

import java.util.*;

public class HelloTest {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setAge(12);
        teacher.setName("mike");
        teacher.eat();
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "gogo");
        map.put(2, "wade");
        map.put(3, "james");
        map.put(4, "curry");
//        for (int key : map.keySet()) {
//            System.out.println(key + map.get(key));
//        }
//        for (String str : map.values()) {
//            System.out.println(str);
//        }
//        System.out.println(map.get(3));

        String array = "2342asfghgyu56asdasdaddddd";
        int[] container = new int[128];
        char[] s=array.toCharArray();
        for(int i=0;i<s.length;i++){
            String newarray=array.substring(0,i)+array.substring(i+1);
//            System.out.println("===="+array.substring(0,i));
//            System.out.println("++++"+array.substring(i+1));
//            System.out.println("////"+array.substring(0,i)+array.substring(i+1));
            if(!newarray.contains(String.valueOf(s[i]))){
                System.out.println(i+"----"+s[i]);
            }
        }




    }
}
