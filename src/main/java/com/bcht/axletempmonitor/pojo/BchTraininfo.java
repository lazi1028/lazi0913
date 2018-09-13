package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class BchTraininfo implements Serializable {
    private Integer id;
    private String trnnum;//列车编号
    private String trntype;//车型
    private String trnattach;//配属
    private String trnsupplier;//厂家
    private List<BchTraincarriage> carriageList;

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

    public String getTrntype() {
        return trntype;
    }

    public void setTrntype(String trntype) {
        this.trntype = trntype;
    }

    public String getTrnattach() {
        return trnattach;
    }

    public void setTrnattach(String trnattach) {
        this.trnattach = trnattach;
    }

    public String getTrnsupplier() {
        return trnsupplier;
    }

    public void setTrnsupplier(String trnsupplier) {
        this.trnsupplier = trnsupplier;
    }

    public List<BchTraincarriage> getCarriageList() {
        return carriageList;
    }

    public void setCarriageList(List<BchTraincarriage> carriageList) {
        this.carriageList = carriageList;
    }
}
