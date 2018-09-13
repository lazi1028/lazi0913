package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BchRoleinfo {
    private Integer roleid;
    private String  rolename;
    private String roledesc;

    private List<BchRolePermission> rolePermissionList;//一个角色多个权限 角色表和角色权限关系表关联

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

    public List<BchRolePermission> getRolePermissionList() {
        return rolePermissionList;
    }

    public void setRolePermissionList(List<BchRolePermission> rolePermissionList) {
        this.rolePermissionList = rolePermissionList;
    }

   /* public BchUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(BchUserRole userRole) {
        this.userRole = userRole;
    }*/
}
