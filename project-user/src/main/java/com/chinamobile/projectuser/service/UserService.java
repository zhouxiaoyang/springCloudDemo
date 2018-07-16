package com.chinamobile.projectuser.service;

import com.chinamobile.projectuser.form.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 17:12 2018/7/5
 * @Modified by:
 */
public interface UserService {
   // public User getUser(String tel, String  password,String code, String uuid);
    public User getUser(Map<String,String> map,HttpServletRequest request );
}
