package com.chinamobile.projectapi.controller;

import com.chinamobile.projectapi.VO.ResultVo;
import com.chinamobile.projectapi.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:21 2018/7/6
 * @Modified by:
 */
@RestController
public class UserController {

    @Autowired
    UserClient userClient;

    @GetMapping("getCode")
    public  void getCode(){
        userClient.getCode( );
    }

//    @PostMapping("login")
//    public Object login(@RequestBody  Map<String,String> map, HttpServletRequest request){
//
//        return userClient.login(map,request);
//    }

//    @GetMapping("getCode")
//    public void getCode(){
//        codeService.getCode();
//    }
}
