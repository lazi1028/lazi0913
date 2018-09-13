package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BchBreakdownCode {
    private Integer id;
    private String brkdwncode;//故障代码
    private String brkdwnname;//故障名称
    private String brkdwnlvl;//故障等级
    private String brkdwncause;//故障原因
    private String  brkdwntrntype;//故障车型
    private String brkdwnsys;//故障系统
    private Date addtime;//添加时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrkdwncode() {
        return brkdwncode;
    }

    public void setBrkdwncode(String brkdwncode) {
        this.brkdwncode = brkdwncode;
    }

    public String getBrkdwnname() {
        return brkdwnname;
    }

    public void setBrkdwnname(String brkdwnname) {
        this.brkdwnname = brkdwnname;
    }

    public String getBrkdwnlvl() {
        return brkdwnlvl;
    }

    public void setBrkdwnlvl(String brkdwnlvl) {
        this.brkdwnlvl = brkdwnlvl;
    }

    public String getBrkdwncause() {
        return brkdwncause;
    }

    public void setBrkdwncause(String brkdwncause) {
        this.brkdwncause = brkdwncause;
    }

    public String getBrkdwntrntype() {
        return brkdwntrntype;
    }

    public void setBrkdwntrntype(String brkdwntrntype) {
        this.brkdwntrntype = brkdwntrntype;
    }

    public String getBrkdwnsys() {
        return brkdwnsys;
    }

    public void setBrkdwnsys(String brkdwnsys) {
        this.brkdwnsys = brkdwnsys;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}
