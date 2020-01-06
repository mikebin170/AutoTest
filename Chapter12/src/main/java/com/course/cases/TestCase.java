package com.course.cases;

import com.course.config.TestConfig;
import com.course.model.InterfaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DatabaseUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TestCase {

    @BeforeTest
    public void beforeTest(){
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceName.LOGIN);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceName.ADDUSERINFO);
        TestConfig.updateUserInfoUrl = ConfigFile.getUrl(InterfaceName.UPDATEUSERINFO);
        TestConfig.getUserInfoUrl  = ConfigFile.getUrl(InterfaceName.GETUSERINFO);
        TestConfig.getUserListUrl = ConfigFile.getUrl(InterfaceName.GETUSERLIST);
    }
    @Test
    public  void loginTrue() throws Exception {
        //获取session
        SqlSession session = DatabaseUtil.getSqlSession();
        //数据库获取用例
        LoginCase loginCase = session.selectOne("loginCase",1) ;
        //发送接口请求
        String result = getResult(loginCase);
        //验证结果

    }

        private String getResult(LoginCase loginCase) throws Exception {
         HttpPost post = new HttpPost(TestConfig.loginUrl);
         JSONObject param = new JSONObject();
         param.put("userName",loginCase.getUserName());
         param.put("password",loginCase.getPassword());
         StringEntity entity = new StringEntity(param.toString());
         post.setEntity(entity);
         post.setHeader("content-type","application/json");
         String result;
         HttpResponse response = TestConfig.defaultHttpClient.execute(post);
         result = EntityUtils.toString(response.getEntity());
         TestConfig.store = TestConfig.defaultHttpClient.getCookieStore();
         return result;
    }
}
