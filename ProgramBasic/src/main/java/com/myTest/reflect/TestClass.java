package com.myTest.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
//使用反射方式初始化类
public class TestClass {

	public static void main(String[] args) throws Exception {

		Class aClass = Class.forName("com.mysql.jdbc.Driver");
		Object object=aClass.newInstance();
		System.out.println(object);
		
		Class clazz = Class.forName("com.myTest.reflect.Person");
		
		Object obj = clazz.newInstance();
		
		//Person obj2= (Person) Class.forName("com.testfan.Person").newInstance();
		
		System.out.println(obj);
		
	 System.out.println(" 对象判断 "+ (obj instanceof Person));

	
	 //反射类的属性
		Field[] fields = clazz.getDeclaredFields();
		for (Field f : fields) {
			System.out.println(f.getName());
		}
//
		//反射类的方法	
//		Method[] methods = clazz.getDeclaredMethods();
//		for (Method method : methods) {
//			System.out.println(method);
//		}
//
//		
		Field field = clazz.getDeclaredField("age");
		field.setAccessible(true);
		field.set(obj, 20);
		System.out.println(obj);
//
//	
		Method m1 = clazz.getMethod("setName", String.class);
		m1.invoke(obj, "zhangsan");
		System.out.println(obj);

	}

	

}
