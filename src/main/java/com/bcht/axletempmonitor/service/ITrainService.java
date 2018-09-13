package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.pojo.*;
import com.bcht.axletempmonitor.vo.ReportVo;
import com.bcht.axletempmonitor.vo.TrainVo;

import java.util.List;
import java.util.Map;

public interface ITrainService {
    public BchTraininfo queryTrainByNum(String trnnum);
    public Map<String,String> queryTrnnumAll();
    public List<BchTraininfo> queryTrnSensor(String trnnum, Integer carrnum);
    public Map<String,String> fuzzyQueryTrnByNum(String trnnum);
    public List<BchSensorTemperature> querySensorTmp(TrainVo tv);
    public List<BchSensorTemperature> querySensorTmpSts(TrainVo tv);

    public List<BchSensorException> querySensorException(TrainVo tv);//查询异常数据
    public String queryCateValue(String catename,String catevalue);

    public BchSensorException queryExcepById(Integer id);//根据id查看异常数据

    public BchSensorException queryBrkdwnInfo(TrainVo tv);

    public int addFile(BchFile file);
    public BchFile getFileById(Integer id);

    //public List<BchSensorException> queryExcepStat(ReportVo rv);
    public List<BchExcepStat> queryExcepStat(ReportVo rv);
    public List<BchExcepStat> queryBrkdwnStsStat(ReportVo rv);
    public List<BchExcepStat> queryBrkdwnTypeStat(ReportVo rv);

    public List<TrainResult> queryTrainFuzzyByNum(TrainVo tv);//列车列表
    public int insertTrain(TrainVo tv);//添加列车
    public TrainResult queryTrainById(TrainVo tv);
    public int updateTrain(TrainVo tv);//修改列车
    public int deleteTrain(TrainVo tv);//删除列车

}
