package com.myTest.xml;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.configuration.XMLConfiguration;


public class XMLReadTest {

	public static void main(String[] args) {
		testReadXML();
	}
	
	public static void testReadXML() {
		try{
			XMLConfiguration config = new XMLConfiguration("config.xml");
			
			String country = config.getString("country");
			System.out.println(country);
			
			//对于循环出现的嵌套元素，可以通过父元素.子元素来获取集合值
			List<Object> address = config.getList("student.address");
			System.out.println("student.address:\t"+Arrays.toString(address.toArray()));
			
			//对于标签元素的属性，可以通过 标签[@属性名]这样的方式来获取
			String age = config.getString("student(0)[@age]");
			System.out.println("student(0)[@age]:\t"+age);
			
			//对于标签里面的属性名称可以这么取
			String fage = config.getString("student(0).friend[@age]");
			System.out.println("student(0).friend[@age]:\t"+fage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
