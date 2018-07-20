package com.coco.projectuser.dao;

import com.coco.projectuser.form.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:56 2018/7/20
 * @Modified by:
 */
@Mapper
public interface AdminDao {
    /**
     * 获取用户列表数量 用于分页
     * @return
     */
    int getUserCount();

    /**
     * query  user list
     * @param start
     * @param pageSize
     * @return
     */
    List<User> getUserList(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    int insertUser(User user);

    int updateUser(User user);

    int removeUser(@Param("id") Integer id);


}
