package com.myTest.restAssueds;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/**
 *
 * https://github.com/rest-assured/rest-assured/wiki/Usage
 * jsonPath
 * https://www.javadoc.io/doc/io.rest-assured/json-path/4.1.2/io/restassured/path/json/JsonPath.html
 * */
public class TestHomeTest {
    @BeforeClass
    public static void setup(){
        useRelaxedHTTPSValidation();
    }

    @Test
    public void testBaidu() {

        given().proxy("127.0.0.1", 8080)
        .queryParam("q", "appium")
        .when().get("https://testerhome.com/search").prettyPeek()
        .then().statusCode(200).body("html.head.title", equalTo("appium · 搜索结果 · TesterHome"));
    }

    /**
     * 在使用findAll用法时,字符串和数组不能对比使用--equalTo
     * topics.findAll { topic -> topic.id == 22094 }.title
     * Expected: 九、Jmeter 关联-Json 提取器
     * Actual: [九、Jmeter 关联-Json 提取器]
     * 需要使用-->hasItem或者find后可使用equalTo
     * */
    @Test
    public void testTestHomeJson(){
        given().when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then()
                .statusCode(200)
                .body("topics.title",hasItem("九、Jmeter 关联-Json 提取器"))
                .body("topics.id[1]",equalTo(22094))
                .body("topics.findAll { topic -> topic.id == 22094 }.title",hasItem("九、Jmeter 关联-Json 提取器"))
                .body("topics.find { topic -> topic.id == 22094 }.title",equalTo("九、Jmeter 关联-Json 提取器"))
        ;
    }
    @Test
    public void testTestHomeJsonSingle(){
        given().when().get("https://testerhome.com/api/v3/topics/10254.json").prettyPeek()
                .then().statusCode(200)
                .body("topic.title",equalTo("优质招聘汇总"));
    }
}
