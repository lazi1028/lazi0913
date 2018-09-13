package com.bcht.axletempmonitor.pojo;

/**
 * 传感器异常数据统计 返回值对象
 */
public class BchExcepStat {

    private String trnnum;//列车编号
    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private String sensorcategory;//传感器种类
    private String excepsts;//异常状态
    private String brkdwntype;//故障类型
    private String brkdwnsts;//故障状态
    private String count1; //汇总数量

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

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

    public String getBrkdwntype() {
        return brkdwntype;
    }

    public void setBrkdwntype(String brkdwntype) {
        this.brkdwntype = brkdwntype;
    }

    public String getBrkdwnsts() {
        return brkdwnsts;
    }

    public void setBrkdwnsts(String brkdwnsts) {
        this.brkdwnsts = brkdwnsts;
    }
}
