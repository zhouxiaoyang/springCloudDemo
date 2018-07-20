package com.chinamobile.projectuser.service;

import com.chinamobile.projectuser.form.User;

import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 15:39 2018/7/20
 * @Modified by:
 */
public interface AdminService {

    Map<String,Object> getUserList(Integer currentPage, Integer pageSize);

    Integer addUser(User user);

    Integer updateUser(User user);

    Integer deleteUser(Integer id);

}
