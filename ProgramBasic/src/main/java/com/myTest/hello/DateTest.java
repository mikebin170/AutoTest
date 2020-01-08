package com.myTest.hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateTest {
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		
		 SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		 System.out.println(ft.format(date));
		 
		 String time ="2012-10-8 15:30:22";
		 Date date2=null;
		 try {
			 date2 = ft.parse(time);
		 } catch (ParseException e) {
			e.printStackTrace();
		 }
		 System.out.println(date2);
		 
		 
		System.out.println(date.getTime());
		
		System.out.println(System.currentTimeMillis());
		
		System.out.println(date.getYear()+1900);
		
		Calendar c= Calendar.getInstance();
		
		System.out.println(c.getWeekYear() + " "+ c.get(Calendar.YEAR));
		
		c.add(Calendar.MINUTE, 15);
		
		c.add(Calendar.HOUR_OF_DAY, 3); 
		
		
		DateUtils.addDays(date, 1);
		DateUtils.addHours(date2, 3);
		
		String time2 =DateFormatUtils.format(date2, "yyyy-MM-dd hh:mm:ss");
		System.out.println(time2);
		
	}

}
