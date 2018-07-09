package com.chinamobile.projectuser.controller;

import com.chinamobile.projectuser.datavo.ResultVo;
import com.chinamobile.projectuser.form.User;
import com.chinamobile.projectuser.service.UserService;
import com.chinamobile.projectuser.util.ResultVoUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 17:02 2018/7/5
 * @Modified by:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    UserService userService;

//    @PostMapping("login")
//    public ResultVo login(User user, String code, String uuid) {
//        User thisUser = userService.getUser(user,code,uuid);
//        if (thisUser == null) {
//            return ResultVoUtil.fail();
//        }
//
//        log.info(thisUser.getUsername() + "登录成功!" + new Date());
//        return ResultVoUtil.success(0, thisUser.getUsername() + " 登录成功!", null);
//        //return ResultVoUtil.success(0,thisUser.getUsername()+" 登录成功!",null);
//    }

    @PostMapping("login")
    public ResultVo login(@RequestBody Map <String,String> map) {
        String tel=map.get("tel");
        String code=map.get("identifyingCode");
        String password=map.get("password");
        String uuid=map.get("uuid");
        System.out.println( tel+":" +code+"--" + password);
        User thisUser = userService.getUser(map);
      //  User thisUser = userService.getUser(user,code,uuid);
        if (thisUser == null) {
            return ResultVoUtil.fail();
        }

        log.info(thisUser.getUsername() + "登录成功!" + new Date());
        //return ResultVoUtil.success(0, " 登录成功!", null);
        return ResultVoUtil.success(0,thisUser.getUsername()+" 登录成功!",null);
    }

}
