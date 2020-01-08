package com.myTest.properties;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {

    private static Properties properties = null;

    static {
        if (null == properties) {
            properties = new Properties();
            InputStreamReader reader = null;
            try {
                reader = new InputStreamReader(PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties"), "utf-8");
                properties.load(reader);
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    reader = null;
                }
            }
        }
    }

    private PropertiesUtils() {
    }

    public static String getString(String key) {

        return properties.getProperty(key, "test");
    }

    public static void main(String[] args) {

        System.out.println("----" + PropertiesUtils.getString("ip"));
    }

}
