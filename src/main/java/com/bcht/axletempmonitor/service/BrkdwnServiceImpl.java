package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.mapper.BrkdwnMapper;
import com.bcht.axletempmonitor.mapper.TrainMapper;
import com.bcht.axletempmonitor.pojo.*;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.vo.BrkdwnVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bcht.axletempmonitor.utils.OtherUtils.getCurrentUser;

@Service
public class BrkdwnServiceImpl implements IBrkdwnService{
    @Autowired
    BrkdwnMapper brkdwnMapper;
    @Autowired
    TrainMapper trainMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int addBrkdwn(BrkdwnVo bv) {
        BchTraininfo traininfo = trainMapper.queryTrainByNum(bv.getTrnnum());
        bv.setTrnattach(traininfo.getTrnattach());//根据列车编号设置配属值
        BchUserinfo currentUser = getCurrentUser();
        if(!MyStringUtils.isEmpty(bv.getBrkdwncode())){
            //根据故障代码查故障等级
        }
        //获取当前用户设置 bv.setBrkdwnadd() ;
        bv.setBrkdwnadd(currentUser.getUsername());
        if(!MyStringUtils.isEmpty(bv.getBrkdwntype()) || !MyStringUtils.isEmpty(bv.getBrkdwncause())){
            //故障原因分析有值 获取当前用户设置 bv.setBrkdwnanaly();
            if(currentUser!=null){
                bv.setBrkdwnanaly(currentUser.getUsername());
            }

        }
        int brk1= brkdwnMapper.addBrkdwn(bv);
        int brkdwnid=bv.getId();
       int brk2=0;
       if(bv.getMaintains()!=null && bv.getMaintains().size()>0){
           for (BchBreakdownMaintain bm: bv.getMaintains()) {
                bm.setId(brkdwnid);
           }
           brk2=brkdwnMapper.addBrkdwnMaintain(bv);
       }
        return brk1+brk2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int addBrkdwnMaintain(BrkdwnVo bv) {
        BchBreakdownInfo brkdwnInfoById = brkdwnMapper.getBrkdwnInfoById(bv.getId()).get(0);
        BchUserinfo currentUser = getCurrentUser();
        bv.setMaintainpers(currentUser.getUsername());
        /*bv.setTrnnum(brkdwnInfoById.getTrnnum());
        bv.setCarrnum(brkdwnInfoById.getCarrnum());
        bv.setSensormainfrm(brkdwnInfoById.getSensormainfrm());
        bv.setSensorcategory(brkdwnInfoById.getSensorcategory());*/
        int i1=0;
        if(bv.getMaintains()!=null && bv.getMaintains().size()>0){
            for (BchBreakdownMaintain bm: bv.getMaintains()) {
                bm.setId(bv.getId());
            }
            i1=brkdwnMapper.addBrkdwnMaintain(bv);
        }
        int i2=0;
        if(!MyStringUtils.isEmpty(bv.getBrkdwnassign())){
            //指派人不为空
           i2 = brkdwnMapper.brkdwnAssign(bv);
        }

        return i1+i2;
    }

    @Override
    public List<BchBreakdownInfo> queryBrkdwnInfo(BrkdwnVo bv) {
        return  brkdwnMapper.queryBrkdwnInfo(bv);
    }

    @Override
    public int brkdwnAssign(BrkdwnVo bv) {
        return brkdwnMapper.brkdwnAssign(bv);
    }

    @Override
    public int addBrkdwnCause(BrkdwnVo bv) {
        return brkdwnMapper.addBrkdwnCause(bv);
    }

    @Override
    public List<BchBreakdownInfo> getBrkdwnInfoById(Integer id) {
        return brkdwnMapper.getBrkdwnInfoById(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updBrkdwnSolve(BrkdwnVo bv) {
        int i1 = brkdwnMapper.updBrkdwnSolve(bv);
        int i2=0;
        if(!MyStringUtils.isEmpty(bv.getBrkdwnassign())){
            //指派人不为空
            i2 = brkdwnMapper.brkdwnAssign(bv);
        }
        return i1+i2;
    }

    @Override
    public int updBrkdwnClose(BrkdwnVo bv) {
        return brkdwnMapper.updBrkdwnClose(bv);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updBrkdwnActive(BrkdwnVo bv) {
        int i1=brkdwnMapper.updBrkdwnActive(bv);
        int i2=0;
        if(!MyStringUtils.isEmpty(bv.getBrkdwnassign())){
            //指派人不为空
            i2 = brkdwnMapper.brkdwnAssign(bv);
        }
        return i1+i2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updBrkdwnInfoById(BrkdwnVo bv) {
        //获取当前用户 brkdwnanaly变为当前用户 bv.setBrkdwnanaly()
        int i1=brkdwnMapper.updBrkdwnInfoById(bv);
        int i2=0;
        int i3=0;
        int i4=0;
        if(bv.getUpdmaintains()!=null && bv.getUpdmaintains().size()>0){
            //更新故障信息时更新维修记录
            i2=brkdwnMapper.updMaintainInfoById(bv);
            //维修记录指派人不为空更新指派人
            if(!MyStringUtils.isEmpty(bv.getBrkdwnassign())){
                i3=brkdwnMapper.brkdwnAssign(bv);
            }
        }
        if(bv.getMaintains()!=null && bv.getMaintains().size()>0){
            //更新时新增维修记录
            if(MyStringUtils.isEmpty(bv.getTrnnum()) && bv.getCarrnum()==null && MyStringUtils.isEmpty(bv.getSensormainfrm())
                    && MyStringUtils.isEmpty(bv.getSensorcategory())){
               i4= addBrkdwnMaintain(bv);
            }
        }
        return i1+i2+i3+i4;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int updMaintainInfoById(BrkdwnVo bv) {
        int i1=0;
        int i2=0;
        //更新维修人 maintainpers
        if(bv.getUpdmaintains()!=null && bv.getUpdmaintains().size()>0){
            //更新故障信息时更新维修记录
            i1=brkdwnMapper.updMaintainInfoById(bv);
            //维修记录指派人不为空更新指派人
            if(!MyStringUtils.isEmpty(bv.getBrkdwnassign())){
                i2=brkdwnMapper.brkdwnAssign(bv);
            }
        }
        return i1+i2;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    public int delBrkdwnInfoBatch(BrkdwnVo bv) {
        int i1=0;
        int i2=0;
        if(bv.getSelectids()!=null && bv.getSelectids().length>0){
          i1= brkdwnMapper.delBrkdwnInfoBatch(bv);
          i2= brkdwnMapper.delMatainInfoBatch(bv);//同时删除维修记录
        }
        return i1+i2;
    }


    @Override
    public int insertBrkdwnCode(BrkdwnVo bv) {
        if(MyStringUtils.isEmpty(bv.getBrkdwnsys())){
            bv.setBrkdwnsys("轴温");
        }
        return brkdwnMapper.insertBrkdwnCode(bv);
    }

    @Override
    public List<BchBreakdownCode> queryBrkdwnCode(BrkdwnVo bv) {
        return brkdwnMapper.queryBrkdwnCode(bv);
    }

    @Override
    public BchBreakdownCode queryBrkdwnCodeById(BrkdwnVo bv) {
        return brkdwnMapper.queryBrkdwnCodeById(bv);
    }

    @Override
    public int updBrkdwnCode(BrkdwnVo bv) {
        if(MyStringUtils.isEmpty(bv.getBrkdwnsys())){
            bv.setBrkdwnsys("轴温");
        }
        return brkdwnMapper.updBrkdwnCode(bv);
    }

    @Override
    public int delBrkdwnCode(BrkdwnVo bv) {
        return brkdwnMapper.delBrkdwnCode(bv);
    }
}
