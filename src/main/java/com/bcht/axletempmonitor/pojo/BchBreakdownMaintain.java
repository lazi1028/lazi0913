package com.bcht.axletempmonitor.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BchBreakdownMaintain {
    private	Integer	mid;//主键
  /*  private	String	trnnum;
    private	Integer	carrnum;
    private	String	sensormainfrm;
    private	String	sensorcategory;*/
    private	String	maintainpers;//维修人

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm")
    private Date maintaintime;//维修时间
    private	String	maintainmethd;//维修方法
    private	String	brkdwndevname;//故障设备名称
    private	String	brkdwndevcnum;//故障设备编号
    private	String	newdevcnum;//新设备编号
    private	String	oldsoftvers;//软件升级前版本号
    private	String	newsoftvers;//升级后版本号
    private	String	detailmethd;//详细处理措施
    private	String	fileid;//附件
    private	Integer id;//与故障表关联的id


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   /* public String getTrnnum() {
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

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }*/

    public String getMaintainpers() {
        return maintainpers;
    }

    public void setMaintainpers(String maintainpers) {
        this.maintainpers = maintainpers;
    }

    public Date getMaintaintime() {
        return maintaintime;
    }

    public void setMaintaintime(Date maintaintime) {
        this.maintaintime = maintaintime;
    }

    public String getMaintainmethd() {
        return maintainmethd;
    }

    public void setMaintainmethd(String maintainmethd) {
        this.maintainmethd = maintainmethd;
    }

    public String getBrkdwndevname() {
        return brkdwndevname;
    }

    public void setBrkdwndevname(String brkdwndevname) {
        this.brkdwndevname = brkdwndevname;
    }

    public String getBrkdwndevcnum() {
        return brkdwndevcnum;
    }

    public void setBrkdwndevcnum(String brkdwndevcnum) {
        this.brkdwndevcnum = brkdwndevcnum;
    }

    public String getNewdevcnum() {
        return newdevcnum;
    }

    public void setNewdevcnum(String newdevcnum) {
        this.newdevcnum = newdevcnum;
    }

    public String getOldsoftvers() {
        return oldsoftvers;
    }

    public void setOldsoftvers(String oldsoftvers) {
        this.oldsoftvers = oldsoftvers;
    }

    public String getNewsoftvers() {
        return newsoftvers;
    }

    public void setNewsoftvers(String newsoftvers) {
        this.newsoftvers = newsoftvers;
    }

    public String getDetailmethd() {
        return detailmethd;
    }

    public void setDetailmethd(String detailmethd) {
        this.detailmethd = detailmethd;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
}
