package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.*;
import com.bcht.axletempmonitor.service.ITrainService;
import com.bcht.axletempmonitor.utils.*;
import com.bcht.axletempmonitor.vo.ReportVo;
import com.bcht.axletempmonitor.vo.TrainVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

import static com.bcht.axletempmonitor.utils.OtherUtils.pageHelperResult;
import static com.bcht.axletempmonitor.utils.OtherUtils.validOriginPwd;

@RestController
@RequestMapping(value = "/train")
public class TrainController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    @Autowired
    ITrainService trainServiceImpl;
    private Integer pageNo;
    private Integer pageSize;

    @RequestMapping(value = "/getTrainByTrnnum/{trnnum}",method = RequestMethod.GET)
    public BchTraininfo getTrainByTrnnum(@PathVariable("trnnum") String trnnum){
        return trainServiceImpl.queryTrainByNum(trnnum);
    }

    //查所有列车编号
    @RequestMapping(value = "/getTrnnumAll",method = RequestMethod.GET)
    public ResultModel getTrnnumAll(){
        Map<String,String> map=trainServiceImpl.queryTrnnumAll();
        return ResultModel.OK(map);
    }

    //查列车车厢对应的传感器种类
    @RequestMapping(value = "/getTrnSensor",method = RequestMethod.POST)
    public ResultModel getTrnSensor(@RequestBody TrainVo tv){
        List<BchTraininfo> traininfoList = trainServiceImpl.queryTrnSensor(tv.getTrnnum(),tv.getCarrnum());
        return ResultModel.OK(traininfoList);
    }
    //列车编号模糊查询
    @RequestMapping(value = "/getTrnnumFuzy/{trnnum}",method = RequestMethod.GET)
    public ResultModel getTrnnumFuzy(@PathVariable("trnnum") String trnnum){
        Map<String,String> map=trainServiceImpl.fuzzyQueryTrnByNum(trnnum);
        return ResultModel.OK(map);
    }

    //查轴温
    @RequestMapping(value = "/getSensorTmp",method = RequestMethod.POST)
    public ResultModel getSensorTmp(@RequestBody TrainVo tv){
        ResultStatus resultStatus = validSensorTmpSts(tv);
        if(resultStatus.getCode()== -3002 || resultStatus.getCode()== -3003){
            return ResultModel.ERROR(resultStatus);
        }
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmp(tv);
        return ResultModel.OK(list);
    }

    @RequestMapping(value = "/getSensorTmpSts",method = RequestMethod.POST)
    public ResultModel getSensorTmpSts(@RequestBody TrainVo tv){
        ResultStatus resultStatus = validSensorTmpSts(tv);
        if(resultStatus.getCode()== -3002 || resultStatus.getCode()== -3003){
            return ResultModel.ERROR(resultStatus);
        }
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmpSts(tv);
        return ResultModel.OK(list);
    }

    //轴温数据导出excel
    @MyLog(value = "轴温数据导出Excel")
    @RequestMapping(value = "/tempExportExcel",method = RequestMethod.POST)
    public void tempExportExcel(@RequestBody TrainVo tv, HttpServletResponse response){
        ResultStatus resultStatus = validSensorTmpSts(tv);
        if(resultStatus.getCode()== -3002 || resultStatus.getCode()== -3003){
            //return ResultModel.ERROR(resultStatus);
            return;
        }
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmpSts(tv);
        String[] headArray={"数据时间","1位轴箱","2位轴箱","3位轴箱","4位轴箱","5位轴箱","6位轴箱","7位轴箱","8位轴箱",
                "1轴小齿轮电机侧轴承","2轴小齿轮电机侧轴承","3轴小齿轮电机侧轴承","4轴小齿轮电机侧轴承","1轴小齿轮车轮侧轴承",
                "2轴小齿轮车轮侧轴承","3轴小齿轮车轮侧轴承","4轴小齿轮车轮侧轴承","1轴大齿轮电机侧轴承","2轴大齿轮电机侧轴承",
                "3轴大齿轮电机侧轴承","4轴大齿轮电机侧轴承","1轴大齿轮车轮侧轴承","2轴大齿轮车轮侧轴承","3轴大齿轮车轮侧轴承",
                "4轴大齿轮车轮侧轴承","1轴电机定子","2轴电机定子","3轴电机定子","4轴电机定子","1轴电机传动端轴承","2轴电机传动端轴承",
                "3轴电机传动端轴承","4轴电机传动端轴承","1轴电机非传动端轴承","2轴电机非传动端轴承","3轴电机非传动端轴承","4轴电机非传动端轴承"};

        List<Object[]> contentList=new ArrayList<>();

        if(list.size()>0){
            for (BchSensorTemperature tmp:list ) {
                BchSensorStatus sensorStatus = tmp.getSensorStatus();
                Object[] obj={
                        tmp.getDatatime1(),tmp.getAb1tmp()+"("+sensorStatus.getAb1sts()+")",tmp.getAb2tmp()+"("+sensorStatus.getAb2sts()+")",
                        tmp.getAb3tmp()+"("+sensorStatus.getAb3sts()+")",tmp.getAb4tmp()+"("+sensorStatus.getAb4sts()+")",tmp.getAb5tmp()+"("+sensorStatus.getAb5sts()+")",
                        tmp.getAb6tmp()+"("+sensorStatus.getAb6sts()+")",tmp.getAb7tmp()+"("+sensorStatus.getAb7sts()+")",tmp.getAb8tmp()+"("+sensorStatus.getAb8sts()+")",
                        tmp.getPemb1tmp()+"("+sensorStatus.getPemb1sts()+")",tmp.getPemb2tmp()+"("+sensorStatus.getPemb2sts()+")",tmp.getPemb3tmp()+"("+sensorStatus.getPemb3sts()+")",tmp.getPemb4tmp()+"("+sensorStatus.getPemb4sts()+")",
                        tmp.getPcwb1tmp()+"("+sensorStatus.getPcwb1sts()+")",tmp.getPcwb2tmp()+"("+sensorStatus.getPcwb2sts()+")",tmp.getPcwb3tmp()+"("+sensorStatus.getPcwb3sts()+")",tmp.getPcwb4tmp()+"("+sensorStatus.getPcwb4sts()+")",
                        tmp.getGemb1tmp()+"("+sensorStatus.getGemb1sts()+")",tmp.getGemb2tmp()+"("+sensorStatus.getGemb2sts()+")",tmp.getGemb3tmp()+"("+sensorStatus.getGemb3sts()+")",tmp.getGemb4tmp()+"("+sensorStatus.getGemb4sts()+")",
                        tmp.getGcwb1tmp()+"("+sensorStatus.getGcwb1sts()+")",tmp.getGcwb2tmp()+"("+sensorStatus.getGcwb2sts()+")",tmp.getGcwb3tmp()+"("+sensorStatus.getGcwb3sts()+")",tmp.getGcwb4tmp()+"("+sensorStatus.getGcwb4sts()+")",
                        tmp.getEms1tmp()+"("+sensorStatus.getEms1sts()+")",tmp.getEms2tmp()+"("+sensorStatus.getEms2sts()+")",tmp.getEms3tmp()+"("+sensorStatus.getEms3sts()+")",tmp.getEms4tmp()+"("+sensorStatus.getEms4sts()+")",
                        tmp.getEmtb1tmp()+"("+sensorStatus.getEmtb1sts()+")",tmp.getEmtb2tmp()+"("+sensorStatus.getEmtb2sts()+")",tmp.getEmtb3tmp()+"("+sensorStatus.getEmtb3sts()+")",tmp.getEmtb4tmp()+"("+sensorStatus.getEmtb4sts()+")",
                        tmp.getEmntb1tmp()+"("+sensorStatus.getEmntb1sts()+")",tmp.getEmntb2tmp()+"("+sensorStatus.getEmntb2sts()+")",tmp.getEmntb3tmp()+"("+sensorStatus.getEmntb3sts()+")",tmp.getEmntb4tmp()+"("+sensorStatus.getEmntb4sts()+")"
                };
                contentList.add(obj);
            }
        }else {
           // return null;
            return;
        }
        try {
            ExcelUtil.ExportExcel(response,headArray,contentList,"轴温数据列表");
        } catch (Exception e) {
            logger.error("导出excel出错....");
            e.printStackTrace();
        }
       // return null;
    }

    //轴温异常数据查询(分页查询)
    @RequestMapping(value = "/getSensorException",method = RequestMethod.POST)
    public ResultModel getSensorException(@RequestBody TrainVo tv){
        if(tv.getPageNo()==null ){
            pageNo=1;
        }else{
            pageNo=tv.getPageNo();
        }
        if(tv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=tv.getPageSize();
        }

        PageHelper.startPage(pageNo,pageSize);
        if(MyStringUtils.isEmpty(tv.getTrnnum()) || MyStringUtils.isEmpty(tv.getStarttime()) || MyStringUtils.isEmpty(tv.getEndtime())){
            return ResultModel.ERROR(ResultStatus.TRAIN_EXCEP_NOT_NULL);
        }
        if(!MyStringUtils.isEmpty(tv.getIflistItem())){
            //查询自定义列表项
            if(MyStringUtils.isEmpty(tv.getTrnnumitem())){
                return ResultModel.ERROR(ResultStatus.TRAIN_EXCEP_ITEM_NULL);
            }
        }
        List<BchSensorException> bchSensorExceptions = trainServiceImpl.querySensorException(tv);
        if(!MyStringUtils.isEmpty(tv.getExportSelected())){
            //导出选中或全部  不使用分页
            return ResultModel.OK(bchSensorExceptions);
        }
        logger.info("pageNo="+pageSize+"--"+"pageSize="+pageSize);

        PageInfo<BchSensorException> result=new PageInfo<BchSensorException>(bchSensorExceptions);
        Map<String,Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }

    //异常数据导出excel
    @MyLog(value = "异常数据导出excel")
    @RequestMapping(value = "/excepExportExcel",method = RequestMethod.POST)
    public void excepExportExcel(@RequestBody TrainVo tv, HttpServletResponse response){
        /*if(!MyStringUtils.isEmpty(tv.getExportSelected()) || "01".equals(tv.getExportSelected())){
            //导出选中
            if(tv.getSelectids()==null){
                return;
            }
        }*/
        List<BchSensorException> bchSensorExceptions = trainServiceImpl.querySensorException(tv);
        if(!MyStringUtils.isEmpty(tv.getIflistItem())){
            //自定义列表项
            if(MyStringUtils.isEmpty(tv.getTrnnumitem())){
               return;
            }else{
               List<String> list=new ArrayList<>();
                if(!MyStringUtils.isEmpty(tv.getTrnnumitem())){
                    list.add("列车编号");
                }
                if(tv.getCarrnumitem()!=null){
                    list.add("车厢号");
                }
                if(!MyStringUtils.isEmpty(tv.getSensormainfrmitem())){
                    list.add("传感器主机");
                }
                if(!MyStringUtils.isEmpty(tv.getSensornameitem())){
                    list.add("传感器名称");
                }
                if(!MyStringUtils.isEmpty(tv.getExcepstarttimeitem())){
                    list.add("异常开始时间");
                }
                if(!MyStringUtils.isEmpty(tv.getExcependtimeitem())){
                    list.add("异常结束时间");
                }
                if(!MyStringUtils.isEmpty(tv.getExcepstsitem())){
                    list.add("异常状态");
                }
                String[] headArray=new String[list.size()];
                for(int i=0;i<list.size();i++){
                    headArray[i]=String.valueOf(list.get(i));
                    logger.info("选中的数据列："+headArray[i]);

                }
               // List<String> list=new ArrayList<>();
                List<String> list1=new ArrayList<>();
                List<Object[]> contentList=new ArrayList<>();
                if(bchSensorExceptions.size()>0){
                    for(int i=0;i<bchSensorExceptions.size();i++){
                        Object[] obj=new String[headArray.length];
                        BchSensorException bse=bchSensorExceptions.get(i);
                        if(!MyStringUtils.isEmpty(tv.getTrnnumitem())){
                            list1.add(bse.getTrnnum());                    }
                        if(tv.getCarrnumitem()!=null){
                            list1.add(String.valueOf(bse.getCarrnum()));
                        }
                        if(!MyStringUtils.isEmpty(tv.getSensormainfrmitem())){
                            list1.add(bse.getSensormainfrm());
                        }
                        if(!MyStringUtils.isEmpty(tv.getSensornameitem())){
                            list1.add(getCateValue("sensorCate",bse.getSensorcategory()));
                        }
                        if(!MyStringUtils.isEmpty(tv.getExcepstarttimeitem())){
                            list1.add(DateUtils.formatString(bse.getExcepstarttime(),"yyyy-MM-dd HH:mm:ss"));
                        }
                        if(!MyStringUtils.isEmpty(tv.getExcependtimeitem())){
                            list1.add(DateUtils.formatString(bse.getExcependtime(),"yyyy-MM-dd HH:mm:ss"));
                        }
                        if(!MyStringUtils.isEmpty(tv.getExcepstsitem())){
                            list1.add(getCateValue("serExcepSts",bse.getExcepsts()));
                        }
                        for(int j=0;j<list1.size();j++){
                            obj[j]=list1.get(j);
                            logger.info("选中列对应的值："+obj[j]);
                        }
                        contentList.add(obj);
                        list1.clear();
                    }
                }
                try {
                    ExcelUtil.ExportExcel(response,headArray,contentList,"轴温异常数据列表");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }else{
           String[] headArray={"传感器名称","异常开始时间","异常结束时间","列车编号","车厢号","传感器主机","异常状态"};
            List<Object[]> contentList=new ArrayList<>();
           if(bchSensorExceptions.size()>0){
               for (BchSensorException item:bchSensorExceptions ) {
                   Object[] obj={
                           getCateValue("sensorCate",item.getSensorcategory()),item.getExcepstarttime(),item.getExcependtime(),item.getTrnnum(),
                           item.getCarrnum(),item.getSensormainfrm(),getCateValue("serExcepSts",item.getExcepsts())
                   };
                   contentList.add(obj);
               }
           }
            try {
                ExcelUtil.ExportExcel(response,headArray,contentList,"轴温异常数据列表");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //根据id查看异常信息
    @RequestMapping(value = "/getExcepById/{id}",method = RequestMethod.GET)
    public ResultModel getExcepById(@PathVariable("id") Integer id){
        return ResultModel.OK(trainServiceImpl.queryExcepById(id));
    }

    //查看指定异常数据前后10/20/30min轴温数据
    @RequestMapping(value = "/getExcepInfo",method = RequestMethod.POST)
    public ResultModel getExcepInfo(@RequestBody TrainVo trainVo) throws NoSuchFieldException {
        BchSensorException bchSensorException = trainServiceImpl.queryExcepById(trainVo.getId());
        String timeParam="";
        if(trainVo.getTimeparams()==null){
            timeParam="01";
        }else{
            timeParam=trainVo.getTimeparams();
        }
        TrainVo tv= getTrainVo(bchSensorException,timeParam);
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmp(tv);
        return ResultModel.OK(list);
    }


    //指定异常数据前后10/20/30min数据导出
    //异常数据导出excel
    @MyLog(value = "某一异常数据前后10/20/30min数据导出excel")
    @RequestMapping(value = "/excepByIdExportExcel",method = RequestMethod.POST)
    public void excepByIdExportExcel(@RequestBody TrainVo trainVo, HttpServletResponse response) throws Exception {
        BchSensorException bchSensorException = trainServiceImpl.queryExcepById(trainVo.getId());
        String timeParam="";
        if(trainVo.getTimeparams()==null){
            timeParam="01";
        }else{
            timeParam=trainVo.getTimeparams();
        }
        TrainVo tv= getTrainVo(bchSensorException,timeParam);
        String[] headArray={"传感器名称","数据时间","传感器温度(状态)"};
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmp(tv);
        List<Object[]> contentList=new ArrayList<>();
        if(list.size()>0){
            for (BchSensorTemperature item:list ) {
                String sensorName=tv.getSensorcategory();
                BchSensorStatus sensorStatus = item.getSensorStatus();

                String filedName1=sensorName+"tmp";
                String filedName2=sensorName+"sts";
                String sensorTmpSts=ReflectUtils.getGetMethod(item, filedName1)+"("+getCateValue("sensorSts",(String) ReflectUtils.getGetMethod(sensorStatus,filedName2))+")";
                //Object getMethod = getGetMethod(item, filedName1);
                ReflectUtils.getGetMethod(sensorStatus,filedName2);
                Object[] obj= {getCateValue("sensorCate", sensorName), item.getDatatime1(),sensorTmpSts};

                contentList.add(obj);
            }
        }
        try {
            ExcelUtil.ExportExcel(response,headArray,contentList,"轴温异常数据列表");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //查看故障数据
    @RequestMapping(value = "/brkdwnInfoView",method = RequestMethod.POST)
    public ResultModel brkdwnInfoView(@RequestBody TrainVo trainVo ) throws NoSuchFieldException {
        if(MyStringUtils.isEmpty(trainVo.getTrnnum()) || MyStringUtils.isEmpty(trainVo.getSensorcategory())
                || MyStringUtils.isEmpty(trainVo.getStarttime())|| trainVo.getCarrnum()==null){
            return ResultModel.ERROR(ResultStatus.BRKDWN_VIEW_NOT_NULL);
        }
        BchTraininfo trainInfo = trainServiceImpl.queryTrainByNum(trainVo.getTrnnum());
        if(trainInfo.getTrntype().contains("标动")){
            if(MyStringUtils.isEmpty(trainVo.getSensormainfrm())){
                return ResultModel.ERROR(ResultStatus.TRAIN_BDMF_NOT_NULL);
            }
        }
        BchSensorException bchSensorException = trainServiceImpl.queryBrkdwnInfo(trainVo);
        String timeParam="";
        if(trainVo.getTimeparams()==null){
            timeParam="01";
        }else{
            timeParam=trainVo.getTimeparams();
        }
        TrainVo tv= getTrainVo(bchSensorException,timeParam);
        List<BchSensorTemperature> list = trainServiceImpl.querySensorTmp(tv);
        return ResultModel.OK(list);
    }


    @RequestMapping(value = "/getExcepStat",method = RequestMethod.POST)
    public ResultModel getExcepStat(@RequestBody ReportVo rv) {
        ResultStatus rs= validReportParam(rv);
        if(rs.getCode()== -3200 || rs.getCode()== -3201){
            return ResultModel.ERROR(rs);
        }
        List<BchExcepStat> list = trainServiceImpl.queryExcepStat(rv);
        return ResultModel.OK(list);
    }

    @RequestMapping(value = "/getBrkdwnStsStat",method = RequestMethod.POST)
    public ResultModel getBrkdwnStsStat(@RequestBody ReportVo rv) {
        ResultStatus rs= validReportParam(rv);
        if(rs.getCode()== -3200 || rs.getCode()== -3201){
            return ResultModel.ERROR(rs);
        }
        List<BchExcepStat> list = trainServiceImpl.queryBrkdwnStsStat(rv);
        return ResultModel.OK(list);
    }

    @RequestMapping(value = "/getBrkdwnTypeStat",method = RequestMethod.POST)
    public ResultModel getBrkdwnTypeStat(@RequestBody ReportVo rv) {
        ResultStatus rs= validReportParam(rv);
        if(rs.getCode()== -3200 || rs.getCode()== -3201){
            return ResultModel.ERROR(rs);
        }
        List<BchExcepStat> list = trainServiceImpl.queryBrkdwnTypeStat(rv);
        return ResultModel.OK(list);
    }

    //列车列表模糊查询
    @RequestMapping(value = "/getTrainFuzzyByNum",method = RequestMethod.POST)
    public ResultModel getTrainFuzzyByNum(@RequestBody TrainVo tv){
        if(tv.getPageNo()==null ){
            pageNo=1;
        }else{
            pageNo=tv.getPageNo();
        }
        if(tv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=tv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);

        List<TrainResult> list = trainServiceImpl.queryTrainFuzzyByNum(tv);
        PageInfo<TrainResult> result=new PageInfo<TrainResult>(list);
        Map<String,Object> map = pageHelperResult(result);
        return ResultModel.OK(map);

    }

    @MyLog(value = "添加列车")
    @RequestMapping(value = "/addTrainInfo",method = RequestMethod.POST)
    public ResultModel addTrainInfo(@RequestBody TrainVo tv){
        if(MyStringUtils.isEmpty(tv.getTrnnum()) || MyStringUtils.isEmpty(tv.getTrntype()) || MyStringUtils.isEmpty(tv.getTrnattach())){
            return ResultModel.ERROR(ResultStatus.TRAINADD_PARAM_NOT_NULL);
        }
        if(!MyStringUtils.isEmpty(tv.getTrnnum()) && !MyStringUtils.validTrnnum(tv.getTrnnum())){
            return ResultModel.ERROR(ResultStatus.TRAINADD_TRNNUM_CHECKED);
        }
        Map<String,String> map=new HashMap<String, String>();
        int i=trainServiceImpl.insertTrain(tv);
        if(i>0){
            map.put("msg","添加列车成功");
        }else {
            map.put("msg","添加列车失败");
        }
        return ResultModel.OK(map);

    }

    @RequestMapping(value = "/getTrainInfoById",method = RequestMethod.POST)
    public ResultModel getTrainInfoById(@RequestBody TrainVo tv){
        Map<String,String> map=new HashMap<String, String>();
        if(tv.getId()==null){
            map.put("msg","参数id必输");
            return ResultModel.OK(map);
        }
        TrainResult traininfo = trainServiceImpl.queryTrainById(tv);
        return ResultModel.OK(traininfo);
    }

    @MyLog(value = "修改列车")
    @RequestMapping(value = "/updateTrainInfo",method = RequestMethod.POST)
    public ResultModel updateTrainInfo(@RequestBody TrainVo tv){
        Map<String,String> map=new HashMap<String, String>();
        if(tv.getId()==null){
            map.put("msg","修改参数id必输");
            return ResultModel.OK(map);
        }
        int i=trainServiceImpl.updateTrain(tv);
        if(i>0){
            map.put("msg","修改列车成功");
        }else {
            map.put("msg","修改列车失败");
        }
        return ResultModel.OK(map);
    }

    @MyLog(value = "删除列车")
    @RequestMapping(value = "/deleteTrainInfo",method = RequestMethod.POST)
    public ResultModel deleteTrainInfo(@RequestBody TrainVo tv){
        Map<String,String> map=new HashMap<String, String>();
        if(tv.getId()==null && (tv.getSelectids()==null || tv.getSelectids().length<=0)){
           return ResultModel.ERROR(ResultStatus.SELECTED_NOT_NULL);
        }

        if(MyStringUtils.isEmpty(tv.getPassword())){
            return  ResultModel.ERROR(ResultStatus.PASSWORD_NOT_NULL);
        }else {
            ResultStatus resultStatus = validOriginPwd(tv.getPassword());
            if(resultStatus.getCode()== -1017 || resultStatus.getCode()== -1100){
                return ResultModel.ERROR(resultStatus);
            }
        }
        int i=trainServiceImpl.deleteTrain(tv);
        if(i>0){
            map.put("msg","删除列车成功");
        }else {
            map.put("msg","删除列车失败");
        }
        return ResultModel.OK(map);
    }

    /**
     * 报表统计 参数 校验
     * @param rv 参数
     * @return ResultStatus
     *
     */
    public ResultStatus validReportParam(ReportVo rv){
        if(MyStringUtils.isEmpty(rv.getStarttime()) || MyStringUtils.isEmpty(rv.getEndtime())){
            return ResultStatus.REPORT_TIME_NOT_NULL;
        }
        if((rv.getTrnnumlist()==null || rv.getTrnnumlist().size()<=0) && (rv.getCarrnumlist()==null || rv.getCarrnumlist().length<=0)
                && (rv.getSensormainfrm()==null || rv.getSensormainfrm().length<=0) && (rv.getSensorlist()==null || rv.getSensorlist().length<=0)){
            return ResultStatus.REPORT_PARAM_NOT_NULL;
        }
        return ResultStatus.SUCCESS;
    }

    /**
     * 校验轴温数据查询条件
     * @param tv 条件参数
     * @return 成功或失败提示
     */
    public ResultStatus validSensorTmpSts(TrainVo tv){
        if(MyStringUtils.isEmpty(tv.getTrnnum()) || tv.getCarrnum()==null || MyStringUtils.isEmpty(tv.getStarttime()) || MyStringUtils.isEmpty(tv.getEndtime())){
            return ResultStatus.TRAINVO_NOT_NULL;
        }
        if(trainServiceImpl.queryTrainByNum(tv.getTrnnum()).getTrntype().contains("标动")) {
            if(MyStringUtils.isEmpty(tv.getSensormainfrm())){
                return ResultStatus.TRAINVO_NOT_NULL;
            }
        }
        Date startDate= DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",tv.getStarttime());
        Date endDate= DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",tv.getEndtime());
        long timegap=3600*1000;
        long time1=endDate.getTime()-startDate.getTime();
        if(time1>timegap || time1<0){
            return ResultStatus.TRAINVO_TIME_GAP;
        }
        return  ResultStatus.SUCCESS;
    }



    //异常信息查看  TrainVo对象作为参数查询轴温
    private TrainVo getTrainVo(BchSensorException bchSensorException,String timeParam) throws NoSuchFieldException {
        TrainVo tv=new TrainVo();
        tv.setTrnnum(bchSensorException.getTrnnum());
        tv.setCarrnum(bchSensorException.getCarrnum());
        if(bchSensorException.getSensormainfrm()!=null){
            tv.setSensormainfrm(bchSensorException.getSensormainfrm());
        }
        tv.setSensorcategory(bchSensorException.getSensorcategory());

        String senname=bchSensorException.getSensorcategory();//既是属性名也是属性值（36个传感器）
        //eg:senname=ab1 对应TrainVo对象属性ab1 setAb1(ab1)
       ReflectUtils.setValue(tv,tv.getClass(),senname,TrainVo.class.getDeclaredField(senname).getType(),senname);
       // tv.setAb1(bchSensorException.getSensorcategory());//传感器名称

        if(timeParam==null|| "".equals(timeParam)){
            timeParam="01";
        }
        Date excepstarttime = bchSensorException.getExcepstarttime();
        if ("01".equals(timeParam)){
            //异常前后10min
            tv.setStarttime(DateUtils.timeToString(excepstarttime.getTime()-10*60*1000,"yyyy-MM-dd HH:mm:ss"));
            tv.setEndtime(DateUtils.timeToString(excepstarttime.getTime()+10*60*1000,"yyyy-MM-dd HH:mm:ss"));
        }else if("02".equals(timeParam)){
            //异常前后20min
            tv.setStarttime(DateUtils.timeToString(excepstarttime.getTime()-20*60*1000,"yyyy-MM-dd HH:mm:ss"));
            tv.setEndtime(DateUtils.timeToString(excepstarttime.getTime()+20*60*1000,"yyyy-MM-dd HH:mm:ss"));
        }else{
            //异常前后30min
            tv.setStarttime(DateUtils.timeToString(excepstarttime.getTime()-30*60*1000,"yyyy-MM-dd HH:mm:ss"));
            tv.setEndtime(DateUtils.timeToString(excepstarttime.getTime()+30*60*1000,"yyyy-MM-dd HH:mm:ss"));
        }
        return tv;
    }

    //查询码值
    public String getCateValue(String catename,String catevalue){
        return trainServiceImpl.queryCateValue(catename,catevalue);
    }


}
