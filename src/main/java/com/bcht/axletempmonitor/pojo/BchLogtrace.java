package com.bcht.axletempmonitor.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

@Component
public class BchLogtrace implements Serializable {
    private BigInteger id;
    private String operator;//操作人
    private String operadetails;//操作内容
    private Date operatime;//操作时间
    private String operafunc;//请求url
    private String requestip;//请求ip

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperadetails() {
        return operadetails;
    }

    public void setOperadetails(String operadetails) {
        this.operadetails = operadetails;
    }

    public Date getOperatime() {
        return operatime;
    }

    public void setOperatime(Date operatime) {
        this.operatime = operatime;
    }

    public String getOperafunc() {
        return operafunc;
    }

    public void setOperafunc(String operafunc) {
        this.operafunc = operafunc;
    }


    public String getRequestip() {
        return requestip;
    }

    public void setRequestip(String requestip) {
        this.requestip = requestip;
    }
}
