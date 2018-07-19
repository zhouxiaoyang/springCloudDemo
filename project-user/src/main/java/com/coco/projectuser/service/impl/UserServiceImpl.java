package com.coco.projectuser.service.impl;

import com.coco.projectuser.dao.UserDao;
import com.coco.projectuser.form.User;
import com.coco.projectuser.service.UserService;
import com.coco.projectuser.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;


/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 17:14 2018/7/5
 * @Modified by:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public User getUser(Map<String,String> map, HttpServletRequest request) {
        //String uuid=map.get("uuid");
        String code=map.get("identifyingCode");

        String uuidByCookie=CookieUtil.get(request,"codeaddress").getValue();
        String codeIn=stringRedisTemplate.opsForValue().get(uuidByCookie);
        System.out.println("codeIn=="+codeIn+"   ---- codeby qian:="+code);
        if (!code.equalsIgnoreCase(codeIn)) {
            System.out.println("验证码不对!!");
            return  null;
        }

        return userDao.getUser(map);
    }


}
