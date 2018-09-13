package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.mapper.TrainMapper;
import com.bcht.axletempmonitor.pojo.*;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.vo.ReportVo;
import com.bcht.axletempmonitor.vo.TrainVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TrainServiceImpl implements ITrainService {

    private static final Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public BchTraininfo queryTrainByNum(String trnnum) {
        String key = "train-" + trnnum;
        ValueOperations<String, BchTraininfo> operations = redisTemplate.opsForValue();

        // 缓存是否存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            BchTraininfo bchTraininfo = operations.get(key);
            logger.info("从缓存中获取了列车信息，key=" + key);
            return bchTraininfo;
        }

        BchTraininfo bchTraininfo = trainMapper.queryTrainByNum(trnnum);
        logger.info("查询数据库结束。。。。。。。。");
        // 插入缓存
        if (bchTraininfo != null) {
            operations.set(key, bchTraininfo, 180, TimeUnit.SECONDS);//设置了缓存3min
            logger.info("列车信息插入缓存，trnnum=" + trnnum);
        }
        return bchTraininfo;
    }

    @Override
    public Map<String, String> queryTrnnumAll() {
        String key = "train-num-typ";
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(key)) {
            Map<String, String> map = redisTemplate.opsForHash().entries(key);
            logger.info("从缓存中获取列车编号及对应车型...");
            return map;
        }
        List<BchTraininfo> list = trainMapper.queryTrnnumAll();
        logger.info("查询数据库结束。。。。。。。。");
        Map<String, String> map1 = new HashMap<String, String>();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //key 列车编号 value 车型
                map1.put(list.get(i).getTrnnum(), list.get(i).getTrntype());
            }
            hashOperations.putAll(key, map1);
            redisTemplate.expire(key,10,TimeUnit.MINUTES);//缓存10min
            logger.info("查出所有列车编号及对应车型插入缓存...");
        }
        return map1;
       /* ListOperations listOperations = redisTemplate.opsForList();
        if(redisTemplate.hasKey(key)){
            List<String> list= (List<String>) listOperations.leftPop(key);
            logger.info("从缓存中获取列车编号..");
            return list;
        }
        List<String> list = trainMapper.queryTrnnumAll();
        if(list.size()>0){
            listOperations.leftPush(key,list);
            logger.info("查出所有列车编号插入缓存...");
        }
        return list;
    }*/
    }

    @Override
    public List<BchTraininfo> queryTrnSensor(String trnnum,Integer carrnum) {
        List<BchTraininfo> traininfo=new ArrayList<>();
        if(!MyStringUtils.isEmpty(trnnum) && carrnum!=null){
            //列车编号和车厢号都有值
            traininfo = trainMapper.queryTrnSensor(trnnum,carrnum);
        }
        return traininfo;
    }

    @Override
    public Map<String,String> fuzzyQueryTrnByNum(String trnnum) {
        String key = "train-num-typ-fuzy";
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(key)) {
            Map<String, String> map = redisTemplate.opsForHash().entries(key);
            logger.info("从缓存中获取列车编号及对应车型...");
            return map;
        }
        List<BchTraininfo> list = trainMapper.fuzzyQueryTrnByNum(trnnum);
        logger.info("模糊查询列车编号 查询数据库结束....");
        Map<String, String> map1 = new HashMap();
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //key 列车编号 value 车型
                map1.put(list.get(i).getTrnnum(),list.get(i).getTrntype());
            }
            hashOperations.putAll(key, map1);
            redisTemplate.expire(key,1,TimeUnit.MINUTES);
            logger.info("查出所有列车编号及对应车型插入缓存...");
        }
        return map1;
    }

    @Override
    public List<BchSensorTemperature> querySensorTmp(TrainVo tv) {
        List<BchSensorTemperature> bchSensorTemperatures = trainMapper.querySensorTmp(tv);
        return bchSensorTemperatures;
    }

    @Override
    public List<BchSensorTemperature> querySensorTmpSts(TrainVo tv) {
        List<BchSensorTemperature> bchSensorTemperatures = trainMapper.querySensorTmpSts(tv);
        return bchSensorTemperatures;
    }

    @Override
    public List<BchSensorException> querySensorException(TrainVo tv) {
        List<BchSensorException> bchSensorExceptions = trainMapper.querySensorException(tv);
        return bchSensorExceptions;
    }

    @Override
    public String queryCateValue(String catename, String catevalue) {
        return trainMapper.getCateValue(catename,catevalue);
    }

    @Override
    public BchSensorException queryExcepById(Integer id) {
        return trainMapper.queryExcepById(id);
    }

    @Override
    public BchSensorException queryBrkdwnInfo(TrainVo tv) {
        return trainMapper.queryBrkdwnInfo(tv);
    }

    @Override
    public int addFile(BchFile file) {
        return trainMapper.addFile(file);
    }

    @Override
    public BchFile getFileById(Integer id) {
        return trainMapper.getFileById(id);
    }

    /*@Override
    public List<BchSensorException> queryExcepStat(ReportVo rv) {
        return trainMapper.queryExcepStat(rv);
    }*/
    @Override
    public List<BchExcepStat> queryExcepStat(ReportVo rv) {
        return trainMapper.queryExcepStat(rv);
    }

    @Override
    public List<BchExcepStat> queryBrkdwnStsStat(ReportVo rv) {
        return trainMapper.queryBrkdwnStsStat(rv);
    }

    @Override
    public List<BchExcepStat> queryBrkdwnTypeStat(ReportVo rv) {
        return trainMapper.queryBrkdwnTypeStat(rv);
    }

    @Override
    public List<TrainResult> queryTrainFuzzyByNum(TrainVo tv) {
        return trainMapper.queryTrainFuzzyByNum(tv);
    }

    @Override
    public int insertTrain(TrainVo tv) {
        if(MyStringUtils.isEmpty(tv.getTrnsupplier())){
            tv.setTrnsupplier("华高");
        }
        return trainMapper.insertTrain(tv);
    }

    @Override
    public TrainResult queryTrainById(TrainVo tv) {
        return trainMapper.queryTrainById(tv);
    }

    @Override
    public int updateTrain(TrainVo tv) {
        if(MyStringUtils.isEmpty(tv.getTrnnum()) && MyStringUtils.isEmpty(tv.getTrntype()) && MyStringUtils.isEmpty(tv.getTrnattach()) && MyStringUtils.isEmpty(tv.getTrnsupplier())){
           //一项未修改
            return 1;
        }
        int i = trainMapper.updateTrain(tv);
        return i;
    }

    @Override
    public int deleteTrain(TrainVo tv) {
        return trainMapper.deleteTrain(tv);
    }
}
