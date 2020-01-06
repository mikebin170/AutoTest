package com.course.cases;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpClientUtils {
    // 设置代理IP、端口、协议（请分别替换）
    private static HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");

    // 把代理设置到请求配置
    private static RequestConfig defaultRequestConfig = RequestConfig.custom().
            setConnectTimeout(30000).build();
    //.setProxy(proxy).build();
    //连接池最大并发连接数
    private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

    static {
        manager.setMaxTotal(200);
    }

    // 实例化CloseableHttpClient对象
    private static CloseableHttpClient closeableHttpClient = HttpClients.custom().
            setConnectionManager(manager)
            .setDefaultRequestConfig(defaultRequestConfig).build();

    public static String doGet(String url, Map<String, Object> headparams) {
        // 构造请求
        HttpGet get = new HttpGet(url);

        // 头部处理
        if (headparams != null && !headparams.isEmpty()) {
            Set<String> keys = headparams.keySet();
            for (String key : keys) {
                get.addHeader(key, headparams.get(key).toString());
            }
        }
        String result = "";
        HttpEntity entity = null;
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(get);
            entity = closeableHttpResponse.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;

    }
    // 重载
    public static String doGet(String url) {
        return doGet(url);
    }

    public static String doPost(String url, Map<String, Object> headparams, Map<String, Object> params) {
        // 构造请求
        HttpPost post = new HttpPost(url);

        // 参数封装
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Set<String> keys = params.keySet();
            for (String key : keys) {
                list.add(new BasicNameValuePair(key, params.get(key).toString()));
            }
            UrlEncodedFormEntity postentity = new UrlEncodedFormEntity(list, Consts.UTF_8);
            post.setEntity(postentity);
        }

        // 头部处理
        if (headparams != null && !headparams.isEmpty()) {
            Set<String> keys = headparams.keySet();
            for (String key : keys) {
                post.addHeader(key, headparams.get(key).toString());
            }
        }

        String result = "";
        HttpEntity entity = null;
        try {
            CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(post);
            entity = closeableHttpResponse.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                EntityUtils.consume(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
