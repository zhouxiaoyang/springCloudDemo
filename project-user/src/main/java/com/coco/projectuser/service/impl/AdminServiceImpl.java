package com.coco.projectuser.service.impl;


import com.coco.projectuser.dao.AdminDao;
import com.coco.projectuser.form.User;
import com.coco.projectuser.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 15:40 2018/7/20
 * @Modified by:
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;

    public Map<String,Object> getUserList(Integer currentPage, Integer pageSize){
        Map<String,Object> map=new HashMap<>();
        int count=adminDao.getUserCount();
        map.put("size",0);
        if (count==0){
            map.put("userList",null);
            return map;
        }
        Integer start=(currentPage-1)*pageSize;
        List<User>users=adminDao.getUserList(start,pageSize);
        map.put("userList",users);
        return  map;
    }

    public Integer addUser(User user){
        return adminDao.insertUser(user);
    }

    public Integer updateUser(User user){
        return  adminDao.updateUser(user);
    }

    public Integer deleteUser(Integer id){
        return  adminDao.removeUser(id);
    }
}
