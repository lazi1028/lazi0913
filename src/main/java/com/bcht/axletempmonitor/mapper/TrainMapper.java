package com.bcht.axletempmonitor.mapper;

import com.bcht.axletempmonitor.pojo.*;
import com.bcht.axletempmonitor.vo.ReportVo;
import com.bcht.axletempmonitor.vo.TrainVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainMapper {
    public BchTraininfo queryTrainByNum(String trnnum); // 通过列车编号查列车信息
    public List<BchTraininfo> queryTrainByTrntype(String trntype);//通过车型查所有列车信息
    public List<BchTraininfo> queryTrnnumAll(); // 查询所有列车编号
    // 根据列车编号/车厢号 查询对应传感器种类 标动车查出两条数(两个传感器主机)
    public List<BchTraininfo> queryTrnSensor(@Param("trnnum")String trnnum, @Param("carrnum")Integer carrnum);
    public List<BchTraininfo> fuzzyQueryTrnByNum(String trnnum); // 列车编号模糊查询
    public List<BchSensorTemperature> querySensorTmp(TrainVo tv); // 查轴温数据
    public List<BchSensorTemperature> querySensorTmpSts(TrainVo tv); // 查轴温及状态数据

    public List<BchSensorException> querySensorException(TrainVo tv);//查询异常数据
    public String getCateValue(@Param("catename")String catename,@Param("catevalue")String catevalue);//查询码值
    public BchSensorException queryExcepById(Integer id);//根据id查看异常数据

    public BchSensorException queryBrkdwnInfo(TrainVo tv);//查看故障数据(根据列车编号、车厢号、传感器主机、传感器名称、时间)

    public int addFile(BchFile file);//附件上传
    public BchFile getFileById(Integer id);//附件上传

    //public List<BchSensorException> queryExcepStat(ReportVo rv);
    public List<BchExcepStat> queryExcepStat(ReportVo rv); //异常数据统计报表
    public List<BchExcepStat> queryBrkdwnStsStat(ReportVo rv);//故障维修统计报表
    public List<BchExcepStat> queryBrkdwnTypeStat(ReportVo rv);//故障类型统计报表

    public List<TrainResult> queryTrainFuzzyByNum(TrainVo tv);//列车列表
    public int insertTrain(TrainVo tv);//添加列车
    public TrainResult queryTrainById(TrainVo tv);
    public int updateTrain(TrainVo tv);//修改列车
    public int deleteTrain(TrainVo tv);//删除列车

}
