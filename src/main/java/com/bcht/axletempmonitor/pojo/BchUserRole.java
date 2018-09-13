package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户角色关系 实体类
 * createdBy lazi 2018/08/16
 */
@Component
public class BchUserRole implements Serializable {
    private Integer id;
    private Integer userid;
    private Integer roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}
