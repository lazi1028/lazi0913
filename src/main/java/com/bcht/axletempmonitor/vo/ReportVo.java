package com.bcht.axletempmonitor.vo;

import com.bcht.axletempmonitor.pojo.BchTraininfo;

import java.util.List;

/**
 * 报表统计 参数封装
 */
public class ReportVo {
    private String starttime;//起始时间
    private String endtime;//结束时间
    private List<BchTraininfo> trnnumlist;//列车编号 eg:trnnumlist:[{trnnum:0201},{trnnum:0202}]
    private Integer[] carrnumlist;//车厢号
    private String[] sensormainfrm;//传感器主机
    private String[] sensorlist;//传感器类型

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

    public List<BchTraininfo> getTrnnumlist() {
        return trnnumlist;
    }

    public void setTrnnumlist(List<BchTraininfo> trnnumlist) {
        this.trnnumlist = trnnumlist;
    }


    public String[] getSensormainfrm() {
        return sensormainfrm;
    }

    public void setSensormainfrm(String[] sensormainfrm) {
        this.sensormainfrm = sensormainfrm;
    }

    public Integer[] getCarrnumlist() {
        return carrnumlist;
    }

    public void setCarrnumlist(Integer[] carrnumlist) {
        this.carrnumlist = carrnumlist;
    }

    public String[] getSensorlist() {
        return sensorlist;
    }

    public void setSensorlist(String[] sensorlist) {
        this.sensorlist = sensorlist;
    }
}
