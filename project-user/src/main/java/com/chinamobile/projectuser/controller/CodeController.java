package com.chinamobile.projectuser.controller;

import com.chinamobile.projectuser.datavo.ResultVo;
import com.chinamobile.projectuser.util.Code;
import com.chinamobile.projectuser.util.CodeUtil;

import com.chinamobile.projectuser.util.ResultVoUtil;
import com.chinamobile.projectuser.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
    private StringRedisTemplate stringRedisTemplate;
    @GetMapping("getUUID")
    public Object getUUID(){
        Map<String,Object>map=new HashMap<>();
        map.put("code",0);
        map.put("uuid",UuidUtil.getUUID());
        return  map;
    }


    @RequestMapping("getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response,String uuid) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        Code code1 = Code.Instance(2);
        //code
        String code = code1.getVerificationCodeValue();
        //String uuid = UuidUtil.getUUID();
        request.setAttribute("uuid", uuid);
        System.out.println("uuid==="+uuid);
        stringRedisTemplate.opsForValue().set(uuid, code, 60, TimeUnit.SECONDS);
        System.out.println("redis code===" + stringRedisTemplate.opsForValue().get(uuid));
        request.getSession().setAttribute("code", code);
       // redisManager.set(MyConstant.SSO_CODE_TIMEOUT_KEY+request.getSession().getId(), num, MyConstant.SSO_CODE_TIMEOUT);
        ServletOutputStream out = null;
        try {
//            code1.getBuffImage().getAlphaRaster().av
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
