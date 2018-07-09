package com.chinamobile.projectuser.service.impl;

import com.chinamobile.projectuser.dao.UserDao;
import com.chinamobile.projectuser.form.User;
import com.chinamobile.projectuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

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
    public User getUser(Map<String,String> map) {
        String uuid=map.get("uuid");
        String code=map.get("identifyingCode");
        String codeIn=stringRedisTemplate.opsForValue().get(uuid);
        System.out.println("codeIn=="+codeIn+"   ---- codeby qian:="+code);
        if (!code.equalsIgnoreCase(codeIn)) {
            System.out.println("验证码不对!!");
            return  null;
        }

      //  System.out.println("sessionID==="+.getSession().getId());
        return userDao.getUser(map);
    }
}
