package com.chinamobile.projectuser.dao;

import com.chinamobile.projectuser.form.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 17:14 2018/7/5
 * @Modified by:
 */
@Mapper
public interface UserDao {

    @Select(" select * from project_user where tel=#{tel} and password=#{password} ")
    User getUser(Map<String ,String > user);
}
