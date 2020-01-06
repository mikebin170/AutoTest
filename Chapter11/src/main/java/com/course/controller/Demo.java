package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "v1",description = "这是我第一个版本的的demo")
@RequestMapping(value = "v1")
public class Demo {
    //首先获取一个执行sql的对象
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/getUserCount",method = RequestMethod.GET)
    @ApiOperation(value = "可以获取到用户数",httpMethod = "GET")
    public int getUserCount(){
       return template.selectOne("getUserCount");
    }
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "新增用戶",httpMethod = "POST")
    public  int addUser(@RequestBody User user){
        int result = template.insert("addUser",user);
        return result;
    }
    @RequestMapping(value = "/updataUser",method = RequestMethod.POST)
    @ApiOperation(value = "更新用戶信息",httpMethod = "POST")
    public int updataUser(@RequestBody User user){
        return  template.update("updateUser",user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.GET)
    public int delUser(@RequestParam int id){
        return template.delete("deleteUser",id);
    }
}
