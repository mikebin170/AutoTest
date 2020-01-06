package com.course.httpclient.cookies;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestResourceBundle {
    public static final String PROPERTIES_FILE_NAME = "application";
    public static final String MY_NAME_KEY = "test.url";
    public static final String MY_VALUE_KEY = "getCookies.uri";

    private static String myName;
    private static String myValue;
    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, Locale.ENGLISH);
            myName = bundle.getString(MY_NAME_KEY).trim();
            myValue = bundle.getString(MY_VALUE_KEY).trim();
        } catch (Exception ex) {
            System.err.println("[Property]:Can't Load property.properties");
        }
    }

    public void print() {
        System.out.println("My name is: " + myName);
        System.out.println("My value is: " + myValue);
    }
    public static void main(String[] args){
        TestResourceBundle testResourceBundle =new TestResourceBundle();
        testResourceBundle.print();
        ResourceBundle resourceBundle=ResourceBundle.getBundle("property");
        System.out.println(resourceBundle.getString("welcome"));
    }


}
