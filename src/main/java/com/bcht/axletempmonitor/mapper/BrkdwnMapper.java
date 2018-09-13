package com.bcht.axletempmonitor.mapper;

import com.bcht.axletempmonitor.pojo.BchBreakdownCode;
import com.bcht.axletempmonitor.pojo.BchBreakdownInfo;
import com.bcht.axletempmonitor.vo.BrkdwnVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrkdwnMapper {
    public int addBrkdwn(BrkdwnVo bv);//添加故障
    public int addBrkdwnMaintain(BrkdwnVo bv);//添加维修记录
    public List<BchBreakdownInfo> queryBrkdwnInfo(BrkdwnVo bv);//查询故障与维修记录信息

    public int brkdwnAssign(BrkdwnVo bv);//故障指派
    public int addBrkdwnCause(BrkdwnVo bv);//添加故障原因分析
    public List<BchBreakdownInfo> getBrkdwnInfoById(Integer id);//根据id查询故障信息

    public int updBrkdwnSolve(BrkdwnVo bv);//故障解决
    public int updBrkdwnClose(BrkdwnVo bv);//故障关闭
    public int updBrkdwnActive(BrkdwnVo bv);//故障激活

    public int updBrkdwnInfoById(BrkdwnVo bv);//故障修改
    public int updMaintainInfoById(BrkdwnVo bv);//维修记录修改

    public int delBrkdwnInfoBatch(BrkdwnVo bv);//删除故障信息
    public int delMatainInfoBatch(BrkdwnVo bv);//删除故障相关的维修记录

    public int insertBrkdwnCode(BrkdwnVo bv);//添加故障代码
    public List<BchBreakdownCode> queryBrkdwnCode(BrkdwnVo bv);//按故障代码或故障名称查询故障代码
    public BchBreakdownCode queryBrkdwnCodeById(BrkdwnVo bv);//通过id查故障代码
    public int updBrkdwnCode(BrkdwnVo bv);//修改故障代码
    public int delBrkdwnCode(BrkdwnVo bv);//删除故障代码


}
