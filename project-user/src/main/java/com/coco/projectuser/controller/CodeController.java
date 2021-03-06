package com.coco.projectuser.controller;


import com.coco.projectuser.util.Code;
import com.coco.projectuser.util.CookieUtil;
import com.coco.projectuser.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 9:32 2018/7/6
 * @Modified by:
 */
@RestController
public class CodeController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        Code code1 = Code.Instance(2);
        //code
        String code = code1.getVerificationCodeValue();
        String uuidCookie;
        if (CookieUtil.get(request, "codeaddress") == null) {
            uuidCookie = UuidUtil.getUUID();
            CookieUtil.addCookie(response, "codeaddress", uuidCookie,-1);
        } else {
            uuidCookie = CookieUtil.get(request, "codeaddress").getValue();
        }
        //一分钟过期
        stringRedisTemplate.opsForValue().set(uuidCookie, code, 60, TimeUnit.SECONDS);
        ServletOutputStream out = null;

        try {
            out = response.getOutputStream();
            ImageIO.write(code1.getBuffImage(), "jpeg", out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
