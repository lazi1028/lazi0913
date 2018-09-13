package com.bcht.axletempmonitor.pojo;

import java.io.Serializable;

public class BchTraincarriage implements Serializable {
    private Integer id;
    private String trnnum;//列车编号
    private Integer carrnum;//车厢号
    private String sensormainfrm;//传感器主机
    private String carrtype;//车厢类型
    private String sensorcategory;//传感器种类

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

    public String getCarrtype() {
        return carrtype;
    }

    public void setCarrtype(String carrtype) {
        this.carrtype = carrtype;
    }

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }
}
