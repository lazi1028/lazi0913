package com.bcht.axletempmonitor.vo;

import com.bcht.axletempmonitor.pojo.BchBreakdownMaintain;

import java.util.List;

public class BrkdwnVo {
    private String trnnum;//列车编号
    private Integer carrnum;//列车车厢号
    private String sensormainfrm;//传感器主机
    private	String	sensorcategory;//传感器类型
    private String  trnattach;//配属
    private List<BchBreakdownMaintain> maintains;//一次添加多条维修记录
    private List<BchBreakdownMaintain> updmaintains;//一次update多条维修记录

    private	String brkdwnname;//故障名称
    private	String	brkdwntype;//故障类型
    private String brkdwntime;//故障时间
    private	String	brkdwncode;//故障代码
    private	String brkdwnlvl;//故障等级
    private	String	brkdwndesc;//故障描述
    private	String brkdwnanaly;//分析人
    private	String	brkdwncause;//故障原因fenxi
    private String maintaintime;//维修时间
    private	String	maintainmethd;//维修方法
    private	String maintainpers;//维修人
    private	String	brkdwnslvtime;//故障解决时间
    private	String	brkdwnslvpers;//解决人
    private	String	brkdwnclstime;//故障关闭时间
    private	String brkdwnclspers;//故障关闭人
    private	String	brkdwnsts;//故障状态
    private	String	brkdwnassign;//故障指派人

    private Integer id;//故障id
   // private Integer[] mid;//维修id

    private String starttime;//开始时间
    private String endtime;//结束时间

    private Integer pageNo;//第几页
    private Integer pageSize;//每页显示几条记录

    private String exportSelected;//导出选中01 导出全部02
    private String[] selectids;//选中数据id

    //自定义列表项参数 默认无值  选中赋值01
    private String iflistItem;//是否选了自定义列表项 是 01 没有值默认查全部列表字段
    private String trnnumitem;//自定义选择 列车编号
    private Integer carrnumitem;//自定义选择 列车车厢号
    private String sensormainfrmitem;//自定义选择 传感器主机
    private	String	sensorcategoryitem;//自定义选择传感器类型
    private String  trnattachitem;//自定义选择 配属
    private	String brkdwnnameitem;//自定义选择 故障名称
    private	String	brkdwntypeitem;//自定义选择 故障类型
    private String brkdwntimeitem;//自定义选择 故障时间
    private	String	brkdwncodeitem;//自定义选择 故障代码
    private	String brkdwnlvlitem;//自定义选择 故障等级
    private	String	brkdwndescitem;//自定义选择 故障描述
    private	String brkdwnanalyitem;//自定义选择 分析人
    private	String	brkdwncauseitem;//自定义选择 故障原因fenxi
    private String maintaintimeitem;//自定义选择 维修时间
    private	String	maintainmethditem;//自定义选择 维修方法
    private	String maintainpersitem;//自定义选择 维修人
    private	String	brkdwnslvtimeitem;//自定义选择 故障解决时间
    private	String	brkdwnslvpersitem;//自定义选择 解决人
    private	String	brkdwnclstimeitem;//自定义选择 故障关闭时间
    private	String brkdwnclspersitem;//自定义选择 故障关闭人
    private	String	brkdwnstsitem;//自定义选择 故障状态
    private	String	brkdwnassignitem;//自定义选择 故障指派人

    private String  brkdwntrntypeitem;//自定义选择 故障车型
    private String brkdwnsysitem;//自定义选择 故障系统

    private	String	causeassign;//原因分析指派人

    private	String	brkdwnadd;//故障添加人
    private	String	brkdwnslvrmk;//解决备注
   // private	String	solveassign;//解决指派人

    private	String	brkdwnclsrmk;//关闭备注

    private	String	brkdwnactitime;//故障激活时间
    private	String	brkdwnactirmk;//激活备注

    private String password;

    private String  brkdwntrntype;//故障车型
    private String brkdwnsys;//故障系统

    public String getBrkdwntrntype() {
        return brkdwntrntype;
    }

    public void setBrkdwntrntype(String brkdwntrntype) {
        this.brkdwntrntype = brkdwntrntype;
    }

    public String getBrkdwnsys() {
        return brkdwnsys;
    }

    public void setBrkdwnsys(String brkdwnsys) {
        this.brkdwnsys = brkdwnsys;
    }

    public String getBrkdwntrntypeitem() {
        return brkdwntrntypeitem;
    }

    public void setBrkdwntrntypeitem(String brkdwntrntypeitem) {
        this.brkdwntrntypeitem = brkdwntrntypeitem;
    }

    public String getBrkdwnsysitem() {
        return brkdwnsysitem;
    }

    public void setBrkdwnsysitem(String brkdwnsysitem) {
        this.brkdwnsysitem = brkdwnsysitem;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getBrkdwnadd() {
        return brkdwnadd;
    }

    public void setBrkdwnadd(String brkdwnadd) {
        this.brkdwnadd = brkdwnadd;
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

    public String getSensorcategory() {
        return sensorcategory;
    }

    public void setSensorcategory(String sensorcategory) {
        this.sensorcategory = sensorcategory;
    }

    public String getTrnattach() {
        return trnattach;
    }

    public void setTrnattach(String trnattach) {
        this.trnattach = trnattach;
    }

    public List<BchBreakdownMaintain> getMaintains() {
        return maintains;
    }

    public void setMaintains(List<BchBreakdownMaintain> maintains) {
        this.maintains = maintains;
    }

    public List<BchBreakdownMaintain> getUpdmaintains() {
        return updmaintains;
    }

    public void setUpdmaintains(List<BchBreakdownMaintain> updmaintains) {
        this.updmaintains = updmaintains;
    }

    public String getBrkdwnname() {
        return brkdwnname;
    }

    public void setBrkdwnname(String brkdwnname) {
        this.brkdwnname = brkdwnname;
    }

    public String getBrkdwntype() {
        return brkdwntype;
    }

    public void setBrkdwntype(String brkdwntype) {
        this.brkdwntype = brkdwntype;
    }

    public String getBrkdwntime() {
        return brkdwntime;
    }

    public void setBrkdwntime(String brkdwntime) {
        this.brkdwntime = brkdwntime;
    }

    public String getBrkdwncode() {
        return brkdwncode;
    }

    public void setBrkdwncode(String brkdwncode) {
        this.brkdwncode = brkdwncode;
    }

    public String getBrkdwnlvl() {
        return brkdwnlvl;
    }

    public void setBrkdwnlvl(String brkdwnlvl) {
        this.brkdwnlvl = brkdwnlvl;
    }

    public String getBrkdwndesc() {
        return brkdwndesc;
    }

    public void setBrkdwndesc(String brkdwndesc) {
        this.brkdwndesc = brkdwndesc;
    }

    public String getBrkdwnanaly() {
        return brkdwnanaly;
    }

    public void setBrkdwnanaly(String brkdwnanaly) {
        this.brkdwnanaly = brkdwnanaly;
    }

    public String getBrkdwncause() {
        return brkdwncause;
    }

    public void setBrkdwncause(String brkdwncause) {
        this.brkdwncause = brkdwncause;
    }

    public String getMaintaintime() {
        return maintaintime;
    }

    public void setMaintaintime(String maintaintime) {
        this.maintaintime = maintaintime;
    }

    public String getMaintainmethd() {
        return maintainmethd;
    }

    public void setMaintainmethd(String maintainmethd) {
        this.maintainmethd = maintainmethd;
    }

    public String getMaintainpers() {
        return maintainpers;
    }

    public void setMaintainpers(String maintainpers) {
        this.maintainpers = maintainpers;
    }

    public String getBrkdwnslvtime() {
        return brkdwnslvtime;
    }

    public void setBrkdwnslvtime(String brkdwnslvtime) {
        this.brkdwnslvtime = brkdwnslvtime;
    }

    public String getBrkdwnslvpers() {
        return brkdwnslvpers;
    }

    public void setBrkdwnslvpers(String brkdwnslvpers) {
        this.brkdwnslvpers = brkdwnslvpers;
    }

    public String getBrkdwnclstime() {
        return brkdwnclstime;
    }

    public void setBrkdwnclstime(String brkdwnclstime) {
        this.brkdwnclstime = brkdwnclstime;
    }

    public String getBrkdwnclspers() {
        return brkdwnclspers;
    }

    public void setBrkdwnclspers(String brkdwnclspers) {
        this.brkdwnclspers = brkdwnclspers;
    }

    public String getBrkdwnsts() {
        return brkdwnsts;
    }

    public void setBrkdwnsts(String brkdwnsts) {
        this.brkdwnsts = brkdwnsts;
    }

    public String getBrkdwnassign() {
        return brkdwnassign;
    }

    public void setBrkdwnassign(String brkdwnassign) {
        this.brkdwnassign = brkdwnassign;
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

    public String getCauseassign() {
        return causeassign;
    }

    public void setCauseassign(String causeassign) {
        this.causeassign = causeassign;
    }

    public String getBrkdwnslvrmk() {
        return brkdwnslvrmk;
    }

    public void setBrkdwnslvrmk(String brkdwnslvrmk) {
        this.brkdwnslvrmk = brkdwnslvrmk;
    }

    public String getBrkdwnclsrmk() {
        return brkdwnclsrmk;
    }

    public void setBrkdwnclsrmk(String brkdwnclsrmk) {
        this.brkdwnclsrmk = brkdwnclsrmk;
    }

    public String getBrkdwnactitime() {
        return brkdwnactitime;
    }

    public void setBrkdwnactitime(String brkdwnactitime) {
        this.brkdwnactitime = brkdwnactitime;
    }

    public String getBrkdwnactirmk() {
        return brkdwnactirmk;
    }

    public void setBrkdwnactirmk(String brkdwnactirmk) {
        this.brkdwnactirmk = brkdwnactirmk;
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

    public String getIflistItem() {
        return iflistItem;
    }

    public void setIflistItem(String iflistItem) {
        this.iflistItem = iflistItem;
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

    public String getSensorcategoryitem() {
        return sensorcategoryitem;
    }

    public void setSensorcategoryitem(String sensorcategoryitem) {
        this.sensorcategoryitem = sensorcategoryitem;
    }

    public String getTrnattachitem() {
        return trnattachitem;
    }

    public void setTrnattachitem(String trnattachitem) {
        this.trnattachitem = trnattachitem;
    }

    public String getBrkdwnnameitem() {
        return brkdwnnameitem;
    }

    public void setBrkdwnnameitem(String brkdwnnameitem) {
        this.brkdwnnameitem = brkdwnnameitem;
    }

    public String getBrkdwntypeitem() {
        return brkdwntypeitem;
    }

    public void setBrkdwntypeitem(String brkdwntypeitem) {
        this.brkdwntypeitem = brkdwntypeitem;
    }

    public String getBrkdwntimeitem() {
        return brkdwntimeitem;
    }

    public void setBrkdwntimeitem(String brkdwntimeitem) {
        this.brkdwntimeitem = brkdwntimeitem;
    }

    public String getBrkdwncodeitem() {
        return brkdwncodeitem;
    }

    public void setBrkdwncodeitem(String brkdwncodeitem) {
        this.brkdwncodeitem = brkdwncodeitem;
    }

    public String getBrkdwnlvlitem() {
        return brkdwnlvlitem;
    }

    public void setBrkdwnlvlitem(String brkdwnlvlitem) {
        this.brkdwnlvlitem = brkdwnlvlitem;
    }

    public String getBrkdwndescitem() {
        return brkdwndescitem;
    }

    public void setBrkdwndescitem(String brkdwndescitem) {
        this.brkdwndescitem = brkdwndescitem;
    }

    public String getBrkdwnanalyitem() {
        return brkdwnanalyitem;
    }

    public void setBrkdwnanalyitem(String brkdwnanalyitem) {
        this.brkdwnanalyitem = brkdwnanalyitem;
    }

    public String getBrkdwncauseitem() {
        return brkdwncauseitem;
    }

    public void setBrkdwncauseitem(String brkdwncauseitem) {
        this.brkdwncauseitem = brkdwncauseitem;
    }

    public String getMaintaintimeitem() {
        return maintaintimeitem;
    }

    public void setMaintaintimeitem(String maintaintimeitem) {
        this.maintaintimeitem = maintaintimeitem;
    }

    public String getMaintainmethditem() {
        return maintainmethditem;
    }

    public void setMaintainmethditem(String maintainmethditem) {
        this.maintainmethditem = maintainmethditem;
    }

    public String getMaintainpersitem() {
        return maintainpersitem;
    }

    public void setMaintainpersitem(String maintainpersitem) {
        this.maintainpersitem = maintainpersitem;
    }

    public String getBrkdwnslvtimeitem() {
        return brkdwnslvtimeitem;
    }

    public void setBrkdwnslvtimeitem(String brkdwnslvtimeitem) {
        this.brkdwnslvtimeitem = brkdwnslvtimeitem;
    }

    public String getBrkdwnslvpersitem() {
        return brkdwnslvpersitem;
    }

    public void setBrkdwnslvpersitem(String brkdwnslvpersitem) {
        this.brkdwnslvpersitem = brkdwnslvpersitem;
    }

    public String getBrkdwnclstimeitem() {
        return brkdwnclstimeitem;
    }

    public void setBrkdwnclstimeitem(String brkdwnclstimeitem) {
        this.brkdwnclstimeitem = brkdwnclstimeitem;
    }

    public String getBrkdwnclspersitem() {
        return brkdwnclspersitem;
    }

    public void setBrkdwnclspersitem(String brkdwnclspersitem) {
        this.brkdwnclspersitem = brkdwnclspersitem;
    }

    public String getBrkdwnstsitem() {
        return brkdwnstsitem;
    }

    public void setBrkdwnstsitem(String brkdwnstsitem) {
        this.brkdwnstsitem = brkdwnstsitem;
    }

    public String getBrkdwnassignitem() {
        return brkdwnassignitem;
    }

    public void setBrkdwnassignitem(String brkdwnassignitem) {
        this.brkdwnassignitem = brkdwnassignitem;
    }

}
