package com.chinamobile.projectapi.controller;

import com.chinamobile.projectapi.VO.ResultVo;
import com.chinamobile.projectapi.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:21 2018/7/6
 * @Modified by:
 */
@RestController
public class UserController {
    @Autowired
    CodeService codeService;

    @PostMapping("login")
    public ResultVo login(String  tel,String password, String code, String uuid){

        return codeService.login(tel,password,code,uuid);
    }

//    @GetMapping("getCode")
//    public void getCode(){
//        codeService.getCode();
//    }
}
