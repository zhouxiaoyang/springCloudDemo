package com.coco.projectuser.form;

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
    private String address;

    public User() {

    }

    public User(String tel, String password, String username, String address) {
        this.tel = tel;
        this.password = password;
        this.username = username;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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
