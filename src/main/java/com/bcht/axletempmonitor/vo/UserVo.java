package com.bcht.axletempmonitor.vo;

import org.springframework.stereotype.Component;

/**
 * 用户 接受参数类型
 */
@Component
public class UserVo {
    private Integer userid;
    private String username;
    private String password;

    private String realname;
    private String sex;
    private String mobilephone;
    private String department;

    private String newpassword;//修改密码时新密码 添加用户时设置密码
    private String repeatnewpassword;//修改密码时新密码 添加用户时确认密码

    private String verifycode;//验证码

    private Integer roleid;
    private Integer pageNo;//当前页
    private Integer pageSize;//每页几条数

    private Integer[] selectids;

    private String rolename;//角色名称
    private String roledesc;//角色描述

    private Integer[] permids;//为角色分配的权限 （或修改时在原权限基础上新增权限）
    private Integer[] updpermids; // 修改角色权限
    private Integer[] delpermids; // 修改角色权限时只是删除部分现有权限
   /*private List<Map<String,Integer>> updpermids;//修改角色分配的权限 参数格式：[{"old":"" ,"new";""}]


    public List<Map<String, Integer>> getUpdpermids() {
        return updpermids;
    }

    public void setUpdpermids(List<Map<String, Integer>> updpermids) {
        this.updpermids = updpermids;
    }*/

    public Integer[] getDelpermids() {
        return delpermids;
    }

    public void setDelpermids(Integer[] delpermids) {
        this.delpermids = delpermids;
    }

    public Integer[] getUpdpermids() {
        return updpermids;
    }

    public void setUpdpermids(Integer[] updpermids) {
        this.updpermids = updpermids;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(String roledesc) {
        this.roledesc = roledesc;
    }

    public Integer[] getPermids() {
        return permids;
    }

    public void setPermids(Integer[] permids) {
        this.permids = permids;
    }

    public Integer[] getSelectids() {
        return selectids;
    }

    public void setSelectids(Integer[] selectids) {
        this.selectids = selectids;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getRepeatnewpassword() {
        return repeatnewpassword;
    }

    public void setRepeatnewpassword(String repeatnewpassword) {
        this.repeatnewpassword = repeatnewpassword;
    }

    public String getVerifycode() {
        return verifycode;
    }

    public void setVerifycode(String verifycode) {
        this.verifycode = verifycode;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
