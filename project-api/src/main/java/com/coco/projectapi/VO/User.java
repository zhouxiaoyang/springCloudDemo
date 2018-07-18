package com.chinamobile.projectapi.VO;

/**
 * @Description: 用户实体类
 * @Author: Winston Yang
 * @Date: Create in 17:09 2018/7/5
 * @Modified by:
 */
public class User {
    private int id;
    private String tel;
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
