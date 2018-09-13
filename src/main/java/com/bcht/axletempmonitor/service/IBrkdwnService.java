package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.pojo.BchBreakdownCode;
import com.bcht.axletempmonitor.pojo.BchBreakdownInfo;
import com.bcht.axletempmonitor.vo.BrkdwnVo;

import java.util.List;

public interface IBrkdwnService {
    public int addBrkdwn(BrkdwnVo bv);
    public int addBrkdwnMaintain(BrkdwnVo bv);
    public List<BchBreakdownInfo> queryBrkdwnInfo(BrkdwnVo bv);

    public int brkdwnAssign(BrkdwnVo bv);
    public int addBrkdwnCause(BrkdwnVo bv);
    public List<BchBreakdownInfo> getBrkdwnInfoById(Integer id);

    public int updBrkdwnSolve(BrkdwnVo bv);
    public int updBrkdwnClose(BrkdwnVo bv);
    public int updBrkdwnActive(BrkdwnVo bv);

    public int updBrkdwnInfoById(BrkdwnVo bv);
    public int updMaintainInfoById(BrkdwnVo bv);

    public int delBrkdwnInfoBatch(BrkdwnVo bv);


    public int insertBrkdwnCode(BrkdwnVo bv);
    public List<BchBreakdownCode> queryBrkdwnCode(BrkdwnVo bv);//按故障代码或故障名称查询故障代码
    public BchBreakdownCode queryBrkdwnCodeById(BrkdwnVo bv);
    public int updBrkdwnCode(BrkdwnVo bv);
    public int delBrkdwnCode(BrkdwnVo bv);
}
