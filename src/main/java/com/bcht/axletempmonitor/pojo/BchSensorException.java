package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BchSensorException {
    private Integer id;
    private String trnnum;//列车编号
    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private Date excepstarttime;//异常开始时间
    private Date excependtime;//异常开始时间
    private String sensorcategory;//传感器种类
    private String excepsts;//异常状态

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

    public Date getExcepstarttime() {
        return excepstarttime;
    }

    public void setExcepstarttime(Date excepstarttime) {
        this.excepstarttime = excepstarttime;
    }

    public Date getExcependtime() {
        return excependtime;
    }

    public void setExcependtime(Date excependtime) {
        this.excependtime = excependtime;
    }

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }

    public String getExcepsts() {
        return excepsts;
    }

    public void setExcepsts(String excepsts) {
        this.excepsts = excepsts;
    }
}
