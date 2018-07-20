package com.coco.projectuser.dao;


import com.coco.projectuser.ProjectUserApplicationTests;
import com.coco.projectuser.form.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 15:04 2018/7/20
 * @Modified by:
 */
@Component
public class AdminDaoTest extends ProjectUserApplicationTests {
    @Autowired
    AdminDao adminDao;

    @Test
    public void getUserCount() throws Exception {
        int sum=adminDao.getUserCount();
        Assert.assertTrue(sum==3);

    }

    @Test
    public void getUserList() throws Exception {
        List<User> users=adminDao.getUserList(0,10);
        Assert.assertTrue(users.size()!=0);
    }

    @Test
    public void insertUser() throws Exception {
        User user=new User("127672672","12345456","aaa","bbbb");
        int result=adminDao.insertUser(user);
        Assert.assertTrue(result==1);
    }

    @Test
    public void updateUser() throws Exception {
        User user=new User("127672672","12345456","aaa","bbbb");
        user.setId(2);
        int result=adminDao.updateUser(user);
        Assert.assertTrue(result==1);
    }

    @Test
    public void removeUser() throws Exception {
        int result=adminDao.removeUser(3);
        Assert.assertTrue(result==1);
    }

}