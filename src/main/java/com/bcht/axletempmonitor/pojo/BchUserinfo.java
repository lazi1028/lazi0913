package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;


@Component
public class BchUserinfo implements Serializable {
    private Integer userid;
    private String username;
    private String password;
    private String realname;
    private String sex;
    private String mobilephone;
    private String department;
    private Date logintime;
    private Date lastlogintime;
    private BchUserRole userRole;//一个用户一个角色 用户表和用户角色关系表关联

    public BchUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(BchUserRole userRole) {
        this.userRole = userRole;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public void setLastlogintime(Date lastlogintime) {
        this.lastlogintime = lastlogintime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public Date getLogintime() {
        return logintime;
    }

    public Date getLastlogintime() {
        return lastlogintime;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
