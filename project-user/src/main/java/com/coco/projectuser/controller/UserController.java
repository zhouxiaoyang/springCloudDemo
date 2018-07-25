package com.coco.projectuser.controller;



import com.coco.projectuser.datavo.ResultVo;
import com.coco.projectuser.form.User;
import com.coco.projectuser.service.UserService;
import com.coco.projectuser.util.CookieUtil;
import com.coco.projectuser.util.ResultVoUtil;
import com.coco.projectuser.util.UuidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("login")
    public ResultVo login(@RequestBody Map <String,String> map, HttpServletRequest request, HttpServletResponse response) {
        User thisUser = userService.getUser(map,request);
        if (thisUser == null) {
            return ResultVoUtil.fail();
        }
        log.info(thisUser.getUsername() + "登录成功!" + new Date());
        String token= UuidUtil.getUUID();
        stringRedisTemplate.opsForValue().set(token,thisUser.getTel(),7200, TimeUnit.SECONDS);
        CookieUtil.addCookie(response,"loginToken",token,7200);
        return ResultVoUtil.success(0,thisUser.getUsername()+" 登录成功!",null);
    }

}
