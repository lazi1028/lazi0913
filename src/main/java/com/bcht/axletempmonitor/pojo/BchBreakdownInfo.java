package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class BchBreakdownInfo {
    private	Integer	id;
    private	String	trnnum;//列车编号
    private	Integer	carrnum;//车厢号
    private	String	sensormainfrm;//传感器主机
    private	String	trnattach;//配属
    private	String	sensorcategory;//传感器类型
    private Date brkdwntime;//故障时间
    private	String	brkdwncode;//故障代码
    private	String brkdwnname;//故障名称
    private	String	brkdwnlvl;//故障等级
    private	String	brkdwndesc;//故障描述
    private	String	brkdwntype;//故障类型
    private	String	brkdwnadd;//故障添加人员
    private	String	brkdwnanaly;//分析人
    private	String	brkdwncause;//故障原因
    private	String	brkdwnassign;//指派人
    private	String	brkdwnsts;//故障状态
    private	Date	brkdwnslvtime;//故障解决时间
    private	String	brkdwnslvrmk;//解决备注
    private	String	brkdwnslvpers;//解决人
    private	Date	brkdwnclstime;//故障关闭时间
    private	String	brkdwnclsrmk;//关闭备注
    private	String	brkdwnclspers;//关闭人
    private	Date	brkdwnactitime;//故障激活时间
    private	String	brkdwnactirmk;//激活备注
    private List<BchBreakdownMaintain> maintainList;//故障与维修一对多

    public String getBrkdwnname() {
        return brkdwnname;
    }

    public void setBrkdwnname(String brkdwnname) {
        this.brkdwnname = brkdwnname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTrnnum() {
        return trnnum;
    }

    public void setTrnnum(String trnnum) {
        this.trnnum = trnnum;
    }

    public Integer getCarrnum() {
        return carrnum;
    }

    public void setCarrnum(Integer carrnum) {
        this.carrnum = carrnum;
    }

    public String getSensormainfrm() {
        return sensormainfrm;
    }

    public void setSensormainfrm(String sensormainfrm) {
        this.sensormainfrm = sensormainfrm;
    }

    public String getTrnattach() {
        return trnattach;
    }

    public void setTrnattach(String trnattach) {
        this.trnattach = trnattach;
    }

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }

    public Date getBrkdwntime() {
        return brkdwntime;
    }

    public void setBrkdwntime(Date brkdwntime) {
        this.brkdwntime = brkdwntime;
    }

    public String getBrkdwncode() {
        return brkdwncode;
    }

    public void setBrkdwncode(String brkdwncode) {
        this.brkdwncode = brkdwncode;
    }

    public String getBrkdwnlvl() {
        return brkdwnlvl;
    }

    public void setBrkdwnlvl(String brkdwnlvl) {
        this.brkdwnlvl = brkdwnlvl;
    }

    public String getBrkdwndesc() {
        return brkdwndesc;
    }

    public void setBrkdwndesc(String brkdwndesc) {
        this.brkdwndesc = brkdwndesc;
    }

    public String getBrkdwntype() {
        return brkdwntype;
    }

    public void setBrkdwntype(String brkdwntype) {
        this.brkdwntype = brkdwntype;
    }

    public String getBrkdwnadd() {
        return brkdwnadd;
    }

    public void setBrkdwnadd(String brkdwnadd) {
        this.brkdwnadd = brkdwnadd;
    }

    public String getBrkdwnanaly() {
        return brkdwnanaly;
    }

    public void setBrkdwnanaly(String brkdwnanaly) {
        this.brkdwnanaly = brkdwnanaly;
    }

    public String getBrkdwncause() {
        return brkdwncause;
    }

    public void setBrkdwncause(String brkdwncause) {
        this.brkdwncause = brkdwncause;
    }

    public String getBrkdwnassign() {
        return brkdwnassign;
    }

    public void setBrkdwnassign(String brkdwnassign) {
        this.brkdwnassign = brkdwnassign;
    }

    public String getBrkdwnsts() {
        return brkdwnsts;
    }

    public void setBrkdwnsts(String brkdwnsts) {
        this.brkdwnsts = brkdwnsts;
    }

    public Date getBrkdwnslvtime() {
        return brkdwnslvtime;
    }

    public void setBrkdwnslvtime(Date brkdwnslvtime) {
        this.brkdwnslvtime = brkdwnslvtime;
    }

    public String getBrkdwnslvrmk() {
        return brkdwnslvrmk;
    }

    public void setBrkdwnslvrmk(String brkdwnslvrmk) {
        this.brkdwnslvrmk = brkdwnslvrmk;
    }

    public String getBrkdwnslvpers() {
        return brkdwnslvpers;
    }

    public void setBrkdwnslvpers(String brkdwnslvpers) {
        this.brkdwnslvpers = brkdwnslvpers;
    }

    public Date getBrkdwnclstime() {
        return brkdwnclstime;
    }

    public void setBrkdwnclstime(Date brkdwnclstime) {
        this.brkdwnclstime = brkdwnclstime;
    }

    public String getBrkdwnclsrmk() {
        return brkdwnclsrmk;
    }

    public void setBrkdwnclsrmk(String brkdwnclsrmk) {
        this.brkdwnclsrmk = brkdwnclsrmk;
    }

    public String getBrkdwnclspers() {
        return brkdwnclspers;
    }

    public void setBrkdwnclspers(String brkdwnclspers) {
        this.brkdwnclspers = brkdwnclspers;
    }

    public Date getBrkdwnactitime() {
        return brkdwnactitime;
    }

    public void setBrkdwnactitime(Date brkdwnactitime) {
        this.brkdwnactitime = brkdwnactitime;
    }

    public String getBrkdwnactirmk() {
        return brkdwnactirmk;
    }

    public void setBrkdwnactirmk(String brkdwnactirmk) {
        this.brkdwnactirmk = brkdwnactirmk;
    }

    public List<BchBreakdownMaintain> getMaintainList() {
        return maintainList;
    }

    public void setMaintainList(List<BchBreakdownMaintain> maintainList) {
        this.maintainList = maintainList;
    }
}
