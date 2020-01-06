package com.course.cases;
import com.course.config.TestConfig;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class Testfan {
    String user_token;

    @BeforeTest
    public void beforTest() throws IOException {
        String url = "http://127.0.0.1:801/DVWA-master/login.php";
//        HttpGet get = new HttpGet(url);
//        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
//        HttpResponse response = defaultHttpClient.execute(get);

        String result = HttpClientUtils.doGet(url);

        //System.out.println("==="+result);
        String rgex = "value='(.*?)' />";
        user_token = PatternTest.getSubUtil(result,rgex);
        System.out.println(user_token);
    }

    @Test
    public void  loginTest() throws Exception {
        String url = "http://127.0.0.1:801/DVWA-master/login.php";
        JSONObject param = new JSONObject();
        param.put("username","admin");
        param.put("password","password");
        param.put("Login","Login");
        param.put("user_token",user_token);
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(param.toString());
        post.setEntity(entity);
        post.setHeader("content-type","pplication/x-www-form-urlencoded; charset=UTF-8");
        String result;
        CookieStore store ;
        //CloseableHttpClient httpclient = HttpClients.createDefault();
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpResponse response = defaultHttpClient.execute(post);
        result = EntityUtils.toString(response.getEntity());
        store = defaultHttpClient.getCookieStore();
        System.out.println(store);
        System.out.println(result);
    }
}
