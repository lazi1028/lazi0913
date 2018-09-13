package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BchSensorStatus {
    private Integer id;
    private String trnnum;//列车编号
    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private Date datatime1;//数据时间

    private String ab1sts; //		1位轴箱
    private String ab2sts; //		2位轴箱
    private String ab3sts; //		3位轴箱
    private String ab4sts; //		4位轴箱
    private String ab5sts; //	5位轴箱
    private String ab6sts; //		6位轴箱
    private String ab7sts; //		7位轴箱
    private String ab8sts; //		8位轴箱
    private String pemb1sts; //		1轴小齿轮电机侧轴承
    private String pemb2sts; //		2轴小齿轮电机侧轴承
    private String pemb3sts; //		3轴小齿轮电机侧轴承
    private String pemb4sts; //		4轴小齿轮电机侧轴承
    private String pcwb1sts; //		1轴小齿轮车轮侧轴承
    private String pcwb2sts; //		2轴小齿轮车轮侧轴承
    private String pcwb3sts; //		3轴小齿轮车轮侧轴承
    private String pcwb4sts; //		4轴小齿轮车轮侧轴承
    private String gemb1sts; //		1轴大齿轮电机侧轴承
    private String gemb2sts; //		2轴大齿轮电机侧轴承
    private String gemb3sts; //		3轴大齿轮电机侧轴承
    private String gemb4sts; //		4轴大齿轮电机侧轴承
    private String gcwb1sts; //		1轴大齿轮车轮侧轴承
    private String gcwb2sts; //		2轴大齿轮车轮侧轴承
    private String gcwb3sts; //		3轴大齿轮车轮侧轴承
    private String gcwb4sts; //		4轴大齿轮车轮侧轴承
    private String ems1sts; //		1轴电机定子
    private String ems2sts; //		2轴电机定子
    private String ems3sts; //		3轴电机定子
    private String ems4sts; //		4轴电机定子
    private String emtb1sts; //		1轴电机传动端轴承
    private String emtb2sts; //		2轴电机传动端轴承
    private String emtb3sts; //		3轴电机传动端轴承
    private String emtb4sts; //		4轴电机传动端轴承
    private String emntb1sts; //		1轴电机非传动端轴承
    private String emntb2sts; //		2轴电机非传动端轴承
    private String emntb3sts; //		3轴电机非传动端轴承
    private String emntb4sts; //		4轴电机非传动端轴承
    private String sensor37sts;
    private String sensor38sts;
    private String sensor39sts;
    private String sensor40sts;
    private String sensor41sts;
    private String sensor42sts;
    private String sensor43sts;
    private String sensor44sts;
    private String sensor45sts;
    private String sensor46sts;
    private String sensor47sts;
    private String sensor48sts;

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

    public String getAb1sts() {
        return ab1sts;
    }

    public void setAb1sts(String ab1sts) {
        this.ab1sts = ab1sts;
    }

    public String getAb2sts() {
        return ab2sts;
    }

    public void setAb2sts(String ab2sts) {
        this.ab2sts = ab2sts;
    }

    public String getAb3sts() {
        return ab3sts;
    }

    public void setAb3sts(String ab3sts) {
        this.ab3sts = ab3sts;
    }

    public String getAb4sts() {
        return ab4sts;
    }

    public void setAb4sts(String ab4sts) {
        this.ab4sts = ab4sts;
    }

    public String getAb5sts() {
        return ab5sts;
    }

    public void setAb5sts(String ab5sts) {
        this.ab5sts = ab5sts;
    }

    public String getAb6sts() {
        return ab6sts;
    }

    public void setAb6sts(String ab6sts) {
        this.ab6sts = ab6sts;
    }

    public String getAb7sts() {
        return ab7sts;
    }

    public void setAb7sts(String ab7sts) {
        this.ab7sts = ab7sts;
    }

    public String getAb8sts() {
        return ab8sts;
    }

    public void setAb8sts(String ab8sts) {
        this.ab8sts = ab8sts;
    }

    public String getPemb1sts() {
        return pemb1sts;
    }

    public void setPemb1sts(String pemb1sts) {
        this.pemb1sts = pemb1sts;
    }

    public String getPemb2sts() {
        return pemb2sts;
    }

    public void setPemb2sts(String pemb2sts) {
        this.pemb2sts = pemb2sts;
    }

    public String getPemb3sts() {
        return pemb3sts;
    }

    public void setPemb3sts(String pemb3sts) {
        this.pemb3sts = pemb3sts;
    }

    public String getPemb4sts() {
        return pemb4sts;
    }

    public void setPemb4sts(String pemb4sts) {
        this.pemb4sts = pemb4sts;
    }

    public String getPcwb1sts() {
        return pcwb1sts;
    }

    public void setPcwb1sts(String pcwb1sts) {
        this.pcwb1sts = pcwb1sts;
    }

    public String getPcwb2sts() {
        return pcwb2sts;
    }

    public void setPcwb2sts(String pcwb2sts) {
        this.pcwb2sts = pcwb2sts;
    }

    public String getPcwb3sts() {
        return pcwb3sts;
    }

    public void setPcwb3sts(String pcwb3sts) {
        this.pcwb3sts = pcwb3sts;
    }

    public String getPcwb4sts() {
        return pcwb4sts;
    }

    public void setPcwb4sts(String pcwb4sts) {
        this.pcwb4sts = pcwb4sts;
    }

    public String getGemb1sts() {
        return gemb1sts;
    }

    public void setGemb1sts(String gemb1sts) {
        this.gemb1sts = gemb1sts;
    }

    public String getGemb2sts() {
        return gemb2sts;
    }

    public void setGemb2sts(String gemb2sts) {
        this.gemb2sts = gemb2sts;
    }

    public String getGemb3sts() {
        return gemb3sts;
    }

    public void setGemb3sts(String gemb3sts) {
        this.gemb3sts = gemb3sts;
    }

    public String getGemb4sts() {
        return gemb4sts;
    }

    public void setGemb4sts(String gemb4sts) {
        this.gemb4sts = gemb4sts;
    }

    public String getGcwb1sts() {
        return gcwb1sts;
    }

    public void setGcwb1sts(String gcwb1sts) {
        this.gcwb1sts = gcwb1sts;
    }

    public String getGcwb2sts() {
        return gcwb2sts;
    }

    public void setGcwb2sts(String gcwb2sts) {
        this.gcwb2sts = gcwb2sts;
    }

    public String getGcwb3sts() {
        return gcwb3sts;
    }

    public void setGcwb3sts(String gcwb3sts) {
        this.gcwb3sts = gcwb3sts;
    }

    public String getGcwb4sts() {
        return gcwb4sts;
    }

    public void setGcwb4sts(String gcwb4sts) {
        this.gcwb4sts = gcwb4sts;
    }

    public String getEms1sts() {
        return ems1sts;
    }

    public void setEms1sts(String ems1sts) {
        this.ems1sts = ems1sts;
    }

    public String getEms2sts() {
        return ems2sts;
    }

    public void setEms2sts(String ems2sts) {
        this.ems2sts = ems2sts;
    }

    public String getEms3sts() {
        return ems3sts;
    }

    public void setEms3sts(String ems3sts) {
        this.ems3sts = ems3sts;
    }

    public String getEms4sts() {
        return ems4sts;
    }

    public void setEms4sts(String ems4sts) {
        this.ems4sts = ems4sts;
    }

    public String getEmtb1sts() {
        return emtb1sts;
    }

    public void setEmtb1sts(String emtb1sts) {
        this.emtb1sts = emtb1sts;
    }

    public String getEmtb2sts() {
        return emtb2sts;
    }

    public void setEmtb2sts(String emtb2sts) {
        this.emtb2sts = emtb2sts;
    }

    public String getEmtb3sts() {
        return emtb3sts;
    }

    public void setEmtb3sts(String emtb3sts) {
        this.emtb3sts = emtb3sts;
    }

    public String getEmtb4sts() {
        return emtb4sts;
    }

    public void setEmtb4sts(String emtb4sts) {
        this.emtb4sts = emtb4sts;
    }

    public String getEmntb1sts() {
        return emntb1sts;
    }

    public void setEmntb1sts(String emntb1sts) {
        this.emntb1sts = emntb1sts;
    }

    public String getEmntb2sts() {
        return emntb2sts;
    }

    public void setEmntb2sts(String emntb2sts) {
        this.emntb2sts = emntb2sts;
    }

    public String getEmntb3sts() {
        return emntb3sts;
    }

    public void setEmntb3sts(String emntb3sts) {
        this.emntb3sts = emntb3sts;
    }

    public String getEmntb4sts() {
        return emntb4sts;
    }

    public void setEmntb4sts(String emntb4sts) {
        this.emntb4sts = emntb4sts;
    }

    public String getSensor37sts() {
        return sensor37sts;
    }

    public void setSensor37sts(String sensor37sts) {
        this.sensor37sts = sensor37sts;
    }

    public String getSensor38sts() {
        return sensor38sts;
    }

    public void setSensor38sts(String sensor38sts) {
        this.sensor38sts = sensor38sts;
    }

    public String getSensor39sts() {
        return sensor39sts;
    }

    public void setSensor39sts(String sensor39sts) {
        this.sensor39sts = sensor39sts;
    }

    public String getSensor40sts() {
        return sensor40sts;
    }

    public void setSensor40sts(String sensor40sts) {
        this.sensor40sts = sensor40sts;
    }

    public String getSensor41sts() {
        return sensor41sts;
    }

    public void setSensor41sts(String sensor41sts) {
        this.sensor41sts = sensor41sts;
    }

    public String getSensor42sts() {
        return sensor42sts;
    }

    public void setSensor42sts(String sensor42sts) {
        this.sensor42sts = sensor42sts;
    }

    public String getSensor43sts() {
        return sensor43sts;
    }

    public void setSensor43sts(String sensor43sts) {
        this.sensor43sts = sensor43sts;
    }

    public String getSensor44sts() {
        return sensor44sts;
    }

    public void setSensor44sts(String sensor44sts) {
        this.sensor44sts = sensor44sts;
    }

    public String getSensor45sts() {
        return sensor45sts;
    }

    public void setSensor45sts(String sensor45sts) {
        this.sensor45sts = sensor45sts;
    }

    public String getSensor46sts() {
        return sensor46sts;
    }

    public void setSensor46sts(String sensor46sts) {
        this.sensor46sts = sensor46sts;
    }

    public String getSensor47sts() {
        return sensor47sts;
    }

    public void setSensor47sts(String sensor47sts) {
        this.sensor47sts = sensor47sts;
    }

    public String getSensor48sts() {
        return sensor48sts;
    }

    public void setSensor48sts(String sensor48sts) {
        this.sensor48sts = sensor48sts;
    }
}
