package com.jsnu.jd.jsnujd.pojo;

import java.util.Date;

/**
 * @author 魏荣轩
 * @date 2020/8/5 14:49
 */
public class User {
    private String uuid;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String avatar;
    private Date lastLoginTime,registerTime;

    public User(){ }

    public User(String uuid, String password, String name, String phone, String email, String avatar, Date lastLoginTime, Date registerTime) {
        this.uuid = uuid;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.lastLoginTime = lastLoginTime;
        this.registerTime = registerTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }
}
