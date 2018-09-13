package com.bcht.axletempmonitor.vo;

/**
 * 列车 请求参数
 */
public class TrainVo {
    private String trnnum;//列车编号
    private String files;//多文件上传
    private String upload1;//上传参数(文件校验完成后点上传 /跳过有错误的上传剩余的文件)

    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private String   carrtype;//车厢类型
    private String sensorcategory;//传感器种类
    private String starttime;//开始时间
    private String endtime;//结束时间

    private Integer pageNo;//第几页
    private Integer pageSize;//每页显示几条记录

    private String exportSelected;//导出选中01 导出全部02
    private String[] selectids;//选中数据id

    private String excepsts;//异常状态
    //自定义列表项参数 默认无值  选中赋值01
    private String iflistItem;//是否选了自定义列表项 是 01 没有值默认查全部列表字段
    private String trnnumitem;//自定义选择 列车编号
    private Integer carrnumitem;//自定义选择列 车车厢号
    private String sensormainfrmitem;//自定义选择 传感器主机
    private String sensornameitem;// 自定义选择 传感器名称
    private String excepstarttimeitem;//自定义选择 异常开始时间
    private String excependtimeitem;//自定义选择 异常结束时间
    private String excepstsitem;//自定义选择 异常状态

    private Integer id;//根据id查看异常数据
    private String timeparams;//查看异常前后10/20/30min数据 分别对应参数01/02/03

    //各传感器对应关系
    private String ab1; //		1位轴箱
    private String ab2; //		2位轴箱
    private String ab3; //		3位轴箱
    private String ab4; //		4位轴箱
    private String ab5; //	5位轴箱
    private String ab6; //		6位轴箱
    private String ab7; //		7位轴箱
    private String ab8; //		8位轴箱
    private String pemb1; //		1轴小齿轮电机侧轴承
    private String pemb2; //		2轴小齿轮电机侧轴承
    private String pemb3; //		3轴小齿轮电机侧轴承
    private String pemb4; //		4轴小齿轮电机侧轴承
    private String pcwb1; //		1轴小齿轮车轮侧轴承
    private String pcwb2; //		2轴小齿轮车轮侧轴承
    private String pcwb3; //		3轴小齿轮车轮侧轴承
    private String pcwb4; //		4轴小齿轮车轮侧轴承
    private String gemb1; //		1轴大齿轮电机侧轴承
    private String gemb2; //		2轴大齿轮电机侧轴承
    private String gemb3; //		3轴大齿轮电机侧轴承
    private String gemb4; //		4轴大齿轮电机侧轴承
    private String gcwb1; //		1轴大齿轮车轮侧轴承
    private String gcwb2; //		2轴大齿轮车轮侧轴承
    private String gcwb3; //		3轴大齿轮车轮侧轴承
    private String gcwb4; //		4轴大齿轮车轮侧轴承
    private String ems1; //		1轴电机定子
    private String ems2; //		2轴电机定子
    private String ems3; //		3轴电机定子
    private String ems4; //		4轴电机定子
    private String emtb1; //		1轴电机传动端轴承
    private String emtb2; //		2轴电机传动端轴承
    private String emtb3; //		3轴电机传动端轴承
    private String emtb4; //		4轴电机传动端轴承
    private String emntb1; //		1轴电机非传动端轴承
    private String emntb2; //		2轴电机非传动端轴承
    private String emntb3; //		3轴电机非传动端轴承
    private String emntb4; //		4轴电机非传动端轴承

    // private String[] sensors;//传感器列表  选中几个数组中添加几个 eg:[ab1,ab2,ab3]等

    private String trntype;//车型
    private String trnattach;//配属
    private String trnsupplier;//厂家
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeparams() {
        return timeparams;
    }

    public void setTimeparams(String timeparams) {
        this.timeparams = timeparams;
    }

    public String getExportSelected() {
        return exportSelected;
    }

    public void setExportSelected(String exportSelected) {
        this.exportSelected = exportSelected;
    }

    public String[] getSelectids() {
        return selectids;
    }

    public void setSelectids(String[] selectids) {
        this.selectids = selectids;
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

    public String getTrnnum() {
        return trnnum;
    }

    public void setTrnnum(String trnnum) {
        this.trnnum = trnnum;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    public String getUpload1() {
        return upload1;
    }

    public void setUpload1(String upload1) {
        this.upload1 = upload1;
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

   /* public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }*/

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

    public String getAb1() {
        return ab1;
    }

    public void setAb1(String ab1) {
        this.ab1 = ab1;
    }

    public String getAb2() {
        return ab2;
    }

    public void setAb2(String ab2) {
        this.ab2 = ab2;
    }

    public String getAb3() {
        return ab3;
    }

    public void setAb3(String ab3) {
        this.ab3 = ab3;
    }

    public String getAb4() {
        return ab4;
    }

    public void setAb4(String ab4) {
        this.ab4 = ab4;
    }

    public String getAb5() {
        return ab5;
    }

    public void setAb5(String ab5) {
        this.ab5 = ab5;
    }

    public String getAb6() {
        return ab6;
    }

    public void setAb6(String ab6) {
        this.ab6 = ab6;
    }

    public String getAb7() {
        return ab7;
    }

    public void setAb7(String ab7) {
        this.ab7 = ab7;
    }

    public String getAb8() {
        return ab8;
    }

    public void setAb8(String ab8) {
        this.ab8 = ab8;
    }

    public String getPemb1() {
        return pemb1;
    }

    public void setPemb1(String pemb1) {
        this.pemb1 = pemb1;
    }

    public String getPemb2() {
        return pemb2;
    }

    public void setPemb2(String pemb2) {
        this.pemb2 = pemb2;
    }

    public String getPemb3() {
        return pemb3;
    }

    public void setPemb3(String pemb3) {
        this.pemb3 = pemb3;
    }

    public String getPemb4() {
        return pemb4;
    }

    public void setPemb4(String pemb4) {
        this.pemb4 = pemb4;
    }

    public String getPcwb1() {
        return pcwb1;
    }

    public void setPcwb1(String pcwb1) {
        this.pcwb1 = pcwb1;
    }

    public String getPcwb2() {
        return pcwb2;
    }

    public void setPcwb2(String pcwb2) {
        this.pcwb2 = pcwb2;
    }

    public String getPcwb3() {
        return pcwb3;
    }

    public void setPcwb3(String pcwb3) {
        this.pcwb3 = pcwb3;
    }

    public String getPcwb4() {
        return pcwb4;
    }

    public void setPcwb4(String pcwb4) {
        this.pcwb4 = pcwb4;
    }

    public String getGemb1() {
        return gemb1;
    }

    public void setGemb1(String gemb1) {
        this.gemb1 = gemb1;
    }

    public String getGemb2() {
        return gemb2;
    }

    public void setGemb2(String gemb2) {
        this.gemb2 = gemb2;
    }

    public String getGemb3() {
        return gemb3;
    }

    public void setGemb3(String gemb3) {
        this.gemb3 = gemb3;
    }

    public String getGemb4() {
        return gemb4;
    }

    public void setGemb4(String gemb4) {
        this.gemb4 = gemb4;
    }

    public String getGcwb1() {
        return gcwb1;
    }

    public void setGcwb1(String gcwb1) {
        this.gcwb1 = gcwb1;
    }

    public String getGcwb2() {
        return gcwb2;
    }

    public void setGcwb2(String gcwb2) {
        this.gcwb2 = gcwb2;
    }

    public String getGcwb3() {
        return gcwb3;
    }

    public void setGcwb3(String gcwb3) {
        this.gcwb3 = gcwb3;
    }

    public String getGcwb4() {
        return gcwb4;
    }

    public void setGcwb4(String gcwb4) {
        this.gcwb4 = gcwb4;
    }

    public String getEms1() {
        return ems1;
    }

    public void setEms1(String ems1) {
        this.ems1 = ems1;
    }

    public String getEms2() {
        return ems2;
    }

    public void setEms2(String ems2) {
        this.ems2 = ems2;
    }

    public String getEms3() {
        return ems3;
    }

    public void setEms3(String ems3) {
        this.ems3 = ems3;
    }

    public String getEms4() {
        return ems4;
    }

    public void setEms4(String ems4) {
        this.ems4 = ems4;
    }

    public String getEmtb1() {
        return emtb1;
    }

    public void setEmtb1(String emtb1) {
        this.emtb1 = emtb1;
    }

    public String getEmtb2() {
        return emtb2;
    }

    public void setEmtb2(String emtb2) {
        this.emtb2 = emtb2;
    }

    public String getEmtb3() {
        return emtb3;
    }

    public void setEmtb3(String emtb3) {
        this.emtb3 = emtb3;
    }

    public String getEmtb4() {
        return emtb4;
    }

    public void setEmtb4(String emtb4) {
        this.emtb4 = emtb4;
    }

    public String getEmntb1() {
        return emntb1;
    }

    public void setEmntb1(String emntb1) {
        this.emntb1 = emntb1;
    }

    public String getEmntb2() {
        return emntb2;
    }

    public void setEmntb2(String emntb2) {
        this.emntb2 = emntb2;
    }

    public String getEmntb3() {
        return emntb3;
    }

    public void setEmntb3(String emntb3) {
        this.emntb3 = emntb3;
    }

    public String getEmntb4() {
        return emntb4;
    }

    public void setEmntb4(String emntb4) {
        this.emntb4 = emntb4;
    }

    public String getExcepsts() {
        return excepsts;
    }

    public void setExcepsts(String excepsts) {
        this.excepsts = excepsts;
    }

    public String getIflistItem() {
        return iflistItem;
    }

    public void setIflistItem(String iflistItem) {
        this.iflistItem = iflistItem;
    }

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }

    public String getTrnnumitem() {
        return trnnumitem;
    }

    public void setTrnnumitem(String trnnumitem) {
        this.trnnumitem = trnnumitem;
    }

    public Integer getCarrnumitem() {
        return carrnumitem;
    }

    public void setCarrnumitem(Integer carrnumitem) {
        this.carrnumitem = carrnumitem;
    }

    public String getSensormainfrmitem() {
        return sensormainfrmitem;
    }

    public void setSensormainfrmitem(String sensormainfrmitem) {
        this.sensormainfrmitem = sensormainfrmitem;
    }

    public String getSensornameitem() {
        return sensornameitem;
    }

    public void setSensornameitem(String sensornameitem) {
        this.sensornameitem = sensornameitem;
    }

    public String getExcepstarttimeitem() {
        return excepstarttimeitem;
    }

    public void setExcepstarttimeitem(String excepstarttimeitem) {
        this.excepstarttimeitem = excepstarttimeitem;
    }

    public String getExcependtimeitem() {
        return excependtimeitem;
    }

    public void setExcependtimeitem(String excependtimeitem) {
        this.excependtimeitem = excependtimeitem;
    }

    public String getExcepstsitem() {
        return excepstsitem;
    }

    public void setExcepstsitem(String excepstsitem) {
        this.excepstsitem = excepstsitem;
    }
}
