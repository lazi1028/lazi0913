package com.bcht.axletempmonitor.vo;

public class LogVo {

    private String operator;//操作人
    private String operadetails;//操作内容
    private String starttime;//操作开始时间
    private String endtime;//操作结束时间
    private Integer pageNo;
    private Integer pageSize;

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

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
