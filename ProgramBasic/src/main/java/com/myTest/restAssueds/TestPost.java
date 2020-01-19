package com.myTest.restAssueds;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class TestPost {

    /**
     * form表单请求方式一
     * 把form表单存到map中，
     * 提取所有cookie供下个接口使用
     */
    @Test
    public void testPostJson() {
        Map<String, Object> bodydata = new HashMap<>();
        bodydata.put("username", "admin");
        bodydata.put("password", "password");
        bodydata.put("Login", "Login");
        bodydata.put("user_token", "b12cfb3dc9cc6c6e718a0afb94fecd51");
        Map<String, String> mapcookies = given().contentType(ContentType.URLENC)
                .formParams(bodydata)
                .when().post("http://127.0.0.1:801/DVWA-master/login.php")
                .then().statusCode(302).extract().cookies();
        for (String cookie : mapcookies.keySet()) {
            String value = mapcookies.get(cookie);//
            System.out.println("key=" + cookie + " value=" + value);
        }

        System.out.println("======================");
        given().cookies(mapcookies).when()
                .get("http://127.0.0.1:801/DVWA-master/instructions.php")
                .prettyPeek()
                .then().statusCode(200);
    }

    /**
     * form表单请求方式二
     * from表单提交
     * 提取部分有用的cookie
     */
    @Test
    public void testPostCookie() {
        Response response = given().contentType(ContentType.URLENC)
                .body("username=admin&password=password&Login=Login&user_token=139f1513a69fa210260ed9a964abb288")
                .when().post("http://127.0.0.1:801/DVWA-master/login.php")
                .then().statusCode(302).extract().response();
        System.out.println(response.cookie("PHPSESSID"));
        System.out.println(response.cookie("impossible"));
    }

    /**
     * from表单提交
     */
    @Test
    public void testPostFrom() {
        Response response = given().contentType(ContentType.URLENC)
                .body("username=admin&password=password&Login=Login&user_token=139f1513a69fa210260ed9a964abb288")
                .when().post("http://127.0.0.1:801/DVWA-master/login.php").prettyPeek()
                .then().statusCode(302).extract().response();
    }

}
