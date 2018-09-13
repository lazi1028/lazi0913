package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BchSensorTemperature {
    private Integer id;
    private String trnnum;//列车编号
    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private Date datatime1;//数据时间

    private String ab1tmp;
    private String ab2tmp;
    private String ab3tmp;
    private String ab4tmp;
    private String ab5tmp;
    private String ab6tmp;
    private String ab7tmp;
    private String ab8tmp;
    private String pemb1tmp;
    private String pemb2tmp;
    private String pemb3tmp;
    private String pemb4tmp;
    private String pcwb1tmp;
    private String pcwb2tmp;
    private String pcwb3tmp;
    private String pcwb4tmp;
    private String gemb1tmp;
    private String gemb2tmp;
    private String gemb3tmp;
    private String gemb4tmp;
    private String gcwb1tmp;
    private String gcwb2tmp;
    private String gcwb3tmp;
    private String gcwb4tmp;
    private String ems1tmp;
    private String ems2tmp;
    private String ems3tmp;
    private String ems4tmp;
    private String emtb1tmp;
    private String emtb2tmp;
    private String emtb3tmp;
    private String emtb4tmp;
    private String emntb1tmp;
    private String emntb2tmp;
    private String emntb3tmp;
    private String emntb4tmp;
    private String sensor37tmp;
    private String sensor38tmp;
    private String sensor39tmp;
    private String sensor40tmp;
    private String sensor41tmp;
    private String sensor42tmp;
    private String sensor43tmp;
    private String sensor44tmp;
    private String sensor45tmp;
    private String sensor46tmp;
    private String sensor47tmp;
    private String sensor48tmp;
    private BchSensorStatus sensorStatus; //传感器温度 与状态一对一

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

    public Date getDatatime1() {
        return datatime1;
    }

    public void setDatatime1(Date datatime1) {
        this.datatime1 = datatime1;
    }

    public String getAb1tmp() {
        return ab1tmp;
    }

    public void setAb1tmp(String ab1tmp) {
        this.ab1tmp = ab1tmp;
    }

    public String getAb2tmp() {
        return ab2tmp;
    }

    public void setAb2tmp(String ab2tmp) {
        this.ab2tmp = ab2tmp;
    }

    public String getAb3tmp() {
        return ab3tmp;
    }

    public void setAb3tmp(String ab3tmp) {
        this.ab3tmp = ab3tmp;
    }

    public String getAb4tmp() {
        return ab4tmp;
    }

    public void setAb4tmp(String ab4tmp) {
        this.ab4tmp = ab4tmp;
    }

    public String getAb5tmp() {
        return ab5tmp;
    }

    public void setAb5tmp(String ab5tmp) {
        this.ab5tmp = ab5tmp;
    }

    public String getAb6tmp() {
        return ab6tmp;
    }

    public void setAb6tmp(String ab6tmp) {
        this.ab6tmp = ab6tmp;
    }

    public String getAb7tmp() {
        return ab7tmp;
    }

    public void setAb7tmp(String ab7tmp) {
        this.ab7tmp = ab7tmp;
    }

    public String getAb8tmp() {
        return ab8tmp;
    }

    public void setAb8tmp(String ab8tmp) {
        this.ab8tmp = ab8tmp;
    }

    public String getPemb1tmp() {
        return pemb1tmp;
    }

    public void setPemb1tmp(String pemb1tmp) {
        this.pemb1tmp = pemb1tmp;
    }

    public String getPemb2tmp() {
        return pemb2tmp;
    }

    public void setPemb2tmp(String pemb2tmp) {
        this.pemb2tmp = pemb2tmp;
    }

    public String getPemb3tmp() {
        return pemb3tmp;
    }

    public void setPemb3tmp(String pemb3tmp) {
        this.pemb3tmp = pemb3tmp;
    }

    public String getPemb4tmp() {
        return pemb4tmp;
    }

    public void setPemb4tmp(String pemb4tmp) {
        this.pemb4tmp = pemb4tmp;
    }

    public String getPcwb1tmp() {
        return pcwb1tmp;
    }

    public void setPcwb1tmp(String pcwb1tmp) {
        this.pcwb1tmp = pcwb1tmp;
    }

    public String getPcwb2tmp() {
        return pcwb2tmp;
    }

    public void setPcwb2tmp(String pcwb2tmp) {
        this.pcwb2tmp = pcwb2tmp;
    }

    public String getPcwb3tmp() {
        return pcwb3tmp;
    }

    public void setPcwb3tmp(String pcwb3tmp) {
        this.pcwb3tmp = pcwb3tmp;
    }

    public String getPcwb4tmp() {
        return pcwb4tmp;
    }

    public void setPcwb4tmp(String pcwb4tmp) {
        this.pcwb4tmp = pcwb4tmp;
    }

    public String getGemb1tmp() {
        return gemb1tmp;
    }

    public void setGemb1tmp(String gemb1tmp) {
        this.gemb1tmp = gemb1tmp;
    }

    public String getGemb2tmp() {
        return gemb2tmp;
    }

    public void setGemb2tmp(String gemb2tmp) {
        this.gemb2tmp = gemb2tmp;
    }

    public String getGemb3tmp() {
        return gemb3tmp;
    }

    public void setGemb3tmp(String gemb3tmp) {
        this.gemb3tmp = gemb3tmp;
    }

    public String getGemb4tmp() {
        return gemb4tmp;
    }

    public void setGemb4tmp(String gemb4tmp) {
        this.gemb4tmp = gemb4tmp;
    }

    public String getGcwb1tmp() {
        return gcwb1tmp;
    }

    public void setGcwb1tmp(String gcwb1tmp) {
        this.gcwb1tmp = gcwb1tmp;
    }

    public String getGcwb2tmp() {
        return gcwb2tmp;
    }

    public void setGcwb2tmp(String gcwb2tmp) {
        this.gcwb2tmp = gcwb2tmp;
    }

    public String getGcwb3tmp() {
        return gcwb3tmp;
    }

    public void setGcwb3tmp(String gcwb3tmp) {
        this.gcwb3tmp = gcwb3tmp;
    }

    public String getGcwb4tmp() {
        return gcwb4tmp;
    }

    public void setGcwb4tmp(String gcwb4tmp) {
        this.gcwb4tmp = gcwb4tmp;
    }

    public String getEms1tmp() {
        return ems1tmp;
    }

    public void setEms1tmp(String ems1tmp) {
        this.ems1tmp = ems1tmp;
    }

    public String getEms2tmp() {
        return ems2tmp;
    }

    public void setEms2tmp(String ems2tmp) {
        this.ems2tmp = ems2tmp;
    }

    public String getEms3tmp() {
        return ems3tmp;
    }

    public void setEms3tmp(String ems3tmp) {
        this.ems3tmp = ems3tmp;
    }

    public String getEms4tmp() {
        return ems4tmp;
    }

    public void setEms4tmp(String ems4tmp) {
        this.ems4tmp = ems4tmp;
    }

    public String getEmtb1tmp() {
        return emtb1tmp;
    }

    public void setEmtb1tmp(String emtb1tmp) {
        this.emtb1tmp = emtb1tmp;
    }

    public String getEmtb2tmp() {
        return emtb2tmp;
    }

    public void setEmtb2tmp(String emtb2tmp) {
        this.emtb2tmp = emtb2tmp;
    }

    public String getEmtb3tmp() {
        return emtb3tmp;
    }

    public void setEmtb3tmp(String emtb3tmp) {
        this.emtb3tmp = emtb3tmp;
    }

    public String getEmtb4tmp() {
        return emtb4tmp;
    }

    public void setEmtb4tmp(String emtb4tmp) {
        this.emtb4tmp = emtb4tmp;
    }

    public String getEmntb1tmp() {
        return emntb1tmp;
    }

    public void setEmntb1tmp(String emntb1tmp) {
        this.emntb1tmp = emntb1tmp;
    }

    public String getEmntb2tmp() {
        return emntb2tmp;
    }

    public void setEmntb2tmp(String emntb2tmp) {
        this.emntb2tmp = emntb2tmp;
    }

    public String getEmntb3tmp() {
        return emntb3tmp;
    }

    public void setEmntb3tmp(String emntb3tmp) {
        this.emntb3tmp = emntb3tmp;
    }

    public String getEmntb4tmp() {
        return emntb4tmp;
    }

    public void setEmntb4tmp(String emntb4tmp) {
        this.emntb4tmp = emntb4tmp;
    }

    public String getSensor37tmp() {
        return sensor37tmp;
    }

    public void setSensor37tmp(String sensor37tmp) {
        this.sensor37tmp = sensor37tmp;
    }

    public String getSensor38tmp() {
        return sensor38tmp;
    }

    public void setSensor38tmp(String sensor38tmp) {
        this.sensor38tmp = sensor38tmp;
    }

    public String getSensor39tmp() {
        return sensor39tmp;
    }

    public void setSensor39tmp(String sensor39tmp) {
        this.sensor39tmp = sensor39tmp;
    }

    public String getSensor40tmp() {
        return sensor40tmp;
    }

    public void setSensor40tmp(String sensor40tmp) {
        this.sensor40tmp = sensor40tmp;
    }

    public String getSensor41tmp() {
        return sensor41tmp;
    }

    public void setSensor41tmp(String sensor41tmp) {
        this.sensor41tmp = sensor41tmp;
    }

    public String getSensor42tmp() {
        return sensor42tmp;
    }

    public void setSensor42tmp(String sensor42tmp) {
        this.sensor42tmp = sensor42tmp;
    }

    public String getSensor43tmp() {
        return sensor43tmp;
    }

    public void setSensor43tmp(String sensor43tmp) {
        this.sensor43tmp = sensor43tmp;
    }

    public String getSensor44tmp() {
        return sensor44tmp;
    }

    public void setSensor44tmp(String sensor44tmp) {
        this.sensor44tmp = sensor44tmp;
    }

    public String getSensor45tmp() {
        return sensor45tmp;
    }

    public void setSensor45tmp(String sensor45tmp) {
        this.sensor45tmp = sensor45tmp;
    }

    public String getSensor46tmp() {
        return sensor46tmp;
    }

    public void setSensor46tmp(String sensor46tmp) {
        this.sensor46tmp = sensor46tmp;
    }

    public String getSensor47tmp() {
        return sensor47tmp;
    }

    public void setSensor47tmp(String sensor47tmp) {
        this.sensor47tmp = sensor47tmp;
    }

    public String getSensor48tmp() {
        return sensor48tmp;
    }

    public void setSensor48tmp(String sensor48tmp) {
        this.sensor48tmp = sensor48tmp;
    }

    public BchSensorStatus getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(BchSensorStatus sensorStatus) {
        this.sensorStatus = sensorStatus;
    }
}
