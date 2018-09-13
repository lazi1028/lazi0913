package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.annotation.MyLog;
import com.bcht.axletempmonitor.pojo.BchBreakdownCode;
import com.bcht.axletempmonitor.pojo.BchBreakdownInfo;
import com.bcht.axletempmonitor.pojo.BchBreakdownMaintain;
import com.bcht.axletempmonitor.pojo.BchFile;
import com.bcht.axletempmonitor.service.IBrkdwnService;
import com.bcht.axletempmonitor.service.ITrainService;
import com.bcht.axletempmonitor.service.IUserService;
import com.bcht.axletempmonitor.utils.*;
import com.bcht.axletempmonitor.vo.BrkdwnVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bcht.axletempmonitor.utils.OtherUtils.pageHelperResult;
import static com.bcht.axletempmonitor.utils.OtherUtils.validOriginPwd;

@RestController
@RequestMapping(value = "/brkdwn")
public class BrkdwnController {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    @Autowired
    IBrkdwnService brkdwnServiceImpl;
    @Autowired
    ITrainService trainServiceImpl;
    @Autowired
    IUserService userServiceImpl;

    private Integer pageNo;
    private Integer pageSize;

    @MyLog(value = "添加故障")
   @RequestMapping(value = "/addBrkDwnInfo",method = RequestMethod.POST)
    public ResultModel addBrkDwnInfo(@RequestBody BrkdwnVo bv){
        int i = brkdwnServiceImpl.addBrkdwn(bv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
           map.put("msg","添加故障成功");
        }else{
            map.put("msg","添加故障失败");
        }
        return ResultModel.OK(map);
    }

    //故障与维修记录 查询(分页查询)
    @RequestMapping(value = "/getBrkdwnInfo",method = RequestMethod.POST)
    public ResultModel getBrkdwnInfo(@RequestBody BrkdwnVo bv){
        if(bv.getPageNo()==null ){
            pageNo=1;
        }else{
            pageNo=bv.getPageNo();
        }
        if(bv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=bv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);
        if(MyStringUtils.isEmpty(bv.getTrnnum()) || MyStringUtils.isEmpty(bv.getBrkdwntype())
                || MyStringUtils.isEmpty(bv.getStarttime()) || MyStringUtils.isEmpty(bv.getEndtime())){
            return ResultModel.ERROR(ResultStatus.BRKDWN_COND_NOT_NULL);
        }
        List<BchBreakdownInfo> bchBreakdownInfos = brkdwnServiceImpl.queryBrkdwnInfo(bv);
        logger.info("pageNo="+pageSize+"--"+"pageSize="+pageSize);
        PageInfo<BchBreakdownInfo> result = new PageInfo<>(bchBreakdownInfos);
        Map<String, Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }

    //故障导出excel
    @MyLog(value = "故障数据导出excel")
    @RequestMapping(value = "/brkdwnExportExcel",method = RequestMethod.POST)
    public void brkdwnExportExcel(@RequestBody BrkdwnVo bv, HttpServletResponse response){
        List<BchBreakdownInfo> bchBreakdownInfos = brkdwnServiceImpl.queryBrkdwnInfo(bv);
        if(!MyStringUtils.isEmpty(bv.getIflistItem())){
            //选了自定义导出列
            String[] headArray=null;
            List<String> list=new ArrayList<>();
            List<String> list1=new ArrayList<>();
            List<Object[]> contentList=new ArrayList<>();
            if(bchBreakdownInfos.size()>0){
                for(int i=0;i<bchBreakdownInfos.size();i++){
                    BchBreakdownInfo brkdwnInfo = bchBreakdownInfos.get(i);
                    if(!MyStringUtils.isEmpty(bv.getTrnnumitem())){
                        if(i==0){
                            list.add("列车编号");
                        }
                        list1.add(brkdwnInfo.getTrnnum());
                    }
                    if(bv.getCarrnumitem()!=null){
                        if(i==0) {
                            list.add("车厢号");
                        }
                        list1.add(String.valueOf(brkdwnInfo.getCarrnum()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getSensormainfrmitem())){
                        if(i==0) {
                            list.add("传感器主机");
                        }
                        list1.add(brkdwnInfo.getSensormainfrm());
                    }
                    if(!MyStringUtils.isEmpty(bv.getSensorcategoryitem())){
                        if(i==0) {
                            list.add("传感器名称");
                        }
                        list1.add(brkdwnInfo.getSensorcategory());
                    }
                    if(!MyStringUtils.isEmpty(bv.getTrnattachitem())){
                        if(i==0) {
                            list.add("配属");
                        }
                        list1.add(brkdwnInfo.getTrnattach());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnnameitem())){
                        if(i==0) {
                            list.add("故障名称");
                        }
                        list1.add(brkdwnInfo.getBrkdwnname());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwntypeitem())){
                        if(i==0) {
                            list.add("故障类型");
                        }
                        list1.add(getCateValue("brkdwnType",brkdwnInfo.getBrkdwntype()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwntimeitem())){
                        if(i==0) {
                            list.add("故障时间");
                        }
                        list1.add(DateUtils.formatString(brkdwnInfo.getBrkdwntime(),"yyyy-MM-dd HH:mm:ss"));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwncodeitem())){
                        if(i==0) {
                            list.add("故障代码");
                        }
                        list1.add(brkdwnInfo.getBrkdwncode());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnlvlitem())){
                        if(i==0) {
                            list.add("故障等级");
                        }
                        list1.add(getCateValue("btkdwnLvl",brkdwnInfo.getBrkdwnlvl()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwndescitem())){
                        if(i==0) {
                            list.add("故障描述");
                        }
                        list1.add(brkdwnInfo.getBrkdwndesc());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnanalyitem())){
                        if(i==0) {
                            list.add("故障分析人");
                        }
                        list1.add(brkdwnInfo.getBrkdwnanaly());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwncauseitem())){
                        if(i==0) {
                            list.add("故障原因分析");
                        }
                        list1.add(brkdwnInfo.getBrkdwncause());
                    }
                    if(!MyStringUtils.isEmpty(bv.getMaintaintimeitem())){
                        if(i==0) {
                            list.add("维修时间");
                        }
                        list1.add(DateUtils.formatString(brkdwnInfo.getMaintainList().get(0).getMaintaintime(),"yyyy-MM-dd HH:mm:ss"));
                    }
                    if(!MyStringUtils.isEmpty(bv.getMaintainmethditem())){
                        if(i==0) {
                            list.add("维修方法");
                        }
                        list1.add(getCateValue("mntnMethod",brkdwnInfo.getMaintainList().get(0).getMaintainmethd()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getMaintainpersitem())){
                        if(i==0) {
                            list.add("维修人");
                        }
                        list1.add(brkdwnInfo.getMaintainList().get(0).getMaintainpers());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnslvtimeitem())){
                        if(i==0) {
                            list.add("故障解决时间");
                        }
                        list1.add(DateUtils.formatString(brkdwnInfo.getBrkdwnslvtime(),"yyyy-MM-dd HH:mm:ss"));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnslvpersitem())){
                        if(i==0) {
                            list.add("解决人员");
                        }
                        list1.add(brkdwnInfo.getBrkdwnslvpers());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnclstimeitem())){
                        if(i==0) {
                            list.add("故障关闭时间");
                        }
                        list1.add(DateUtils.formatString(brkdwnInfo.getBrkdwnclstime(),"yyyy-MM-dd HH:mm:ss"));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnclspersitem())){
                        if(i==0) {
                            list.add("关闭人员");
                        }
                        list1.add(brkdwnInfo.getBrkdwnclspers());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnstsitem())){
                        if(i==0) {
                            list.add("故障状态");
                        }
                        list1.add(getCateValue("brkdwnSts",brkdwnInfo.getBrkdwnsts()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnassignitem())){
                        if(i==0) {
                            list.add("当前指派人");
                        }
                        list1.add(brkdwnInfo.getBrkdwnassign());
                    }

                    if(i==0){
                        if(list!=null && list.size()>0){
                            headArray=new String[list.size()];
                            for (int k=0;k<list.size();k++){
                                headArray[k]=String.valueOf(list.get(k));
                                logger.info("选中的数据列："+headArray[k]);
                            }
                        }
                    }
                    if(list1!=null && list1.size()>0){
                        Object[] obj=new String[list1.size()];
                        for(int j=0;j<list1.size();j++){
                            obj[j]=list1.get(j);
                            logger.info("选中列对应的值："+obj[j]);
                        }
                        contentList.add(obj);
                        list1.clear();
                    }
                }

                try {
                    ExcelUtil.ExportExcel(response,headArray,contentList,"故障与维修记录");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            String[] headArray={"列车编号","车厢号","传感器主机","传感器名称","配属","故障名称","故障类型","故障时间","故障代码","故障等级","故障描述",
                    "故障分析人","故障原因分析","维修时间","维修方法","维修人","故障解决时间","解决人员","故障关闭时间","关闭人员","故障状态","故障指派人"};
            List<Object[]> contentList=new ArrayList<>();
            if(bchBreakdownInfos.size()>0){
                for (BchBreakdownInfo item : bchBreakdownInfos  ) {
                    BchBreakdownMaintain maintain = item.getMaintainList().get(0);
                    Object[] obj={
                            item.getTrnnum(),item.getCarrnum(),item.getSensormainfrm(),item.getSensorcategory(),item.getTrnattach(),item.getBrkdwnname(),
                            getCateValue("brkdwnType",item.getBrkdwntype()),DateUtils.formatString(item.getBrkdwntime(),"yyyy-MM-dd HH:mm:ss"),item.getBrkdwncode(),
                            getCateValue("btkdwnLvl",item.getBrkdwnlvl()),item.getBrkdwndesc(),item.getBrkdwnanaly(), item.getBrkdwncause(),
                            DateUtils.formatString(maintain.getMaintaintime(),"yyyy-MM-dd HH:mm:ss"),getCateValue("mntnMethod",maintain.getMaintainmethd()),
                            maintain.getMaintainpers(),DateUtils.formatString(item.getBrkdwnslvtime(),"yyyy-MM-dd HH:mm:ss"),item.getBrkdwnslvpers(),
                            DateUtils.formatString(item.getBrkdwnclstime(),"yyyy-MM-dd HH:mm:ss"),item.getBrkdwnclspers(),
                            getCateValue("brkdwnSts",item.getBrkdwnsts()),item.getBrkdwnassign()
                   };
                    contentList.add(obj);
                }
                try {
                    ExcelUtil.ExportExcel(response,headArray,contentList,"故障与维修记录");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

   /* 查看故障数据 即查看异常数据前后10/20/30min轴温数据
    请求：train/brkdwnInfoView 参数TrainVo tv（
    trnnum carrnum sensormainfrm  sensorcategory starttime timeparams）*/

   //故障指派
   @MyLog(value = "故障指派")
   @RequestMapping(value = "/updBrkdwnAssign",method = RequestMethod.POST)
    public ResultModel updBrkdwnAssign(@RequestBody BrkdwnVo bv){
       int i = brkdwnServiceImpl.brkdwnAssign(bv);
       Map<String,String> map=new HashMap<String, String>();
       if(i>0){
           map.put("msg","故障指派成功");
       }else {
           map.put("msg","故障指派失败");
       }
       return ResultModel.OK(map);
   }

   //添加故障原因分析
   @MyLog(value = "添加故障原因分析")
   @RequestMapping(value = "/addBrkdwnCause",method = RequestMethod.POST)
   public ResultModel addBrkdwnCause(@RequestBody BrkdwnVo bv){
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        Map<String,String> map=new HashMap<String, String>();
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            if(!MyStringUtils.isEmpty( brkdwnInfoById.get(0).getBrkdwntype()) && !MyStringUtils.isEmpty( brkdwnInfoById.get(0).getBrkdwncause())
            && !MyStringUtils.isEmpty(brkdwnInfoById.get(0).getBrkdwnassign())){
                map.put("msg","添加失败（故障原因已添加过，不能在添加了）");
                return ResultModel.OK(map);
            }
        }
        int i = brkdwnServiceImpl.addBrkdwnCause(bv);

        if(i>0){
            map.put("msg","添加原因分析成功");
        }else {
            map.put("msg","添加原因分析失败");
        }
        return ResultModel.OK(map);
    }

    //添加维修记录
    @MyLog(value = "添加维修记录")
    @RequestMapping(value = "/addMaintainInfo",method = RequestMethod.POST)
    public ResultModel addMaintainInfo(@RequestBody BrkdwnVo bv){
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        Map<String,String> map=new HashMap<String, String>();
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            if("02".equals(brkdwnInfoById.get(0).getBrkdwnsts()) || "03".equals(brkdwnInfoById.get(0).getBrkdwnsts())){
                //故障已解决
                map.put("msg","故障已解决或关闭，不能添加维修记录");
                return ResultModel.OK(map);
            }
        }
        int i = brkdwnServiceImpl.addBrkdwnMaintain(bv);
        if(i>0){
            map.put("msg","添加维修记录成功");
        }else {
            map.put("msg","添加维修记录失败");
        }
        return ResultModel.OK(map);
    }

    //故障解决
    @MyLog(value = "故障解决")
    @RequestMapping(value = "/updBrkdwnSolve",method = RequestMethod.POST)
    public ResultModel updBrkdwnSolve(@RequestBody BrkdwnVo bv){
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        Map<String,String> map=new HashMap<String, String>();
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            if(MyStringUtils.isEmpty( brkdwnInfoById.get(0).getBrkdwntype()) && MyStringUtils.isEmpty( brkdwnInfoById.get(0).getBrkdwncause())){
                map.put("msg","故障原因分析还未完成，只有添加完原因分析后才可点故障解决");
                return ResultModel.OK(map);
            }
            if("02".equals(brkdwnInfoById.get(0).getBrkdwnsts()) || "03".equals(brkdwnInfoById.get(0).getBrkdwnsts())){
                map.put("msg","故障已经解决或关闭，不可再点击");
                return ResultModel.OK(map);
            }
        }
       int i= brkdwnServiceImpl.updBrkdwnSolve(bv);
        if(i>0){
            map.put("msg","故障解决成功");
        }else {
            map.put("msg","故障解决失败");
        }
        return ResultModel.OK(map);
    }

    //故障关闭
    @MyLog(value = "故障关闭")
    @RequestMapping(value = "/updBrkdwnClose",method = RequestMethod.POST)
    public ResultModel updBrkdwnClose(@RequestBody BrkdwnVo bv){
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        Map<String,String> map=new HashMap<String, String>();
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            if("03".equals(brkdwnInfoById.get(0).getBrkdwnsts())){
                map.put("msg","故障已经关闭，不可再点击");
                return ResultModel.OK(map);
            }
            if(!"02".equals(brkdwnInfoById.get(0).getBrkdwnsts())){
                map.put("msg","故障还未解决，只有在故障解决后才可关闭故障");
                return ResultModel.OK(map);
            }
        }
        int i=brkdwnServiceImpl.updBrkdwnClose(bv);
        if(i>0){
            map.put("msg","故障关闭成功");
        }else {
            map.put("msg","故障关闭失败");
        }
        return ResultModel.OK(map);
    }

    //故障激活
    @MyLog(value = "故障激活")
    @RequestMapping(value = "/updBrkdwnActive",method = RequestMethod.POST)
    public ResultModel updBrkdwnActive(@RequestBody BrkdwnVo bv){
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        Map<String,String> map=new HashMap<String, String>();
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            if(!"03".equals(brkdwnInfoById.get(0).getBrkdwnsts())){
                map.put("msg","故障还未关闭，只有关闭故障后才可激活故障");
                return ResultModel.OK(map);
            }
        }
        int i=brkdwnServiceImpl.updBrkdwnActive(bv);
        if(i>0){
            map.put("msg","故障激活成功");
        }else {
            map.put("msg","故障激活失败");
        }
        return ResultModel.OK(map);
    }

    //通过id查故障信息（查看故障详情）
    @RequestMapping(value = "/getBrkdwnInfoById",method = RequestMethod.POST)
    public ResultModel getBrkdwnInfoById(@RequestBody BrkdwnVo bv){
      // PageHelper.startPage(bv.getPageNo(),bv.getPageSize());
        List<BchBreakdownInfo> brkdwnInfoById = getBrkdwnInfoById(bv.getId());
        if(brkdwnInfoById!=null && brkdwnInfoById.size()>0){
            for (BchBreakdownInfo bd:brkdwnInfoById ) {
                String fid=bd.getMaintainList().get(0).getFileid();
                if(!MyStringUtils.isEmpty(fid)){
                    //显示附件信息 fileid=fileid+filename+filepath
                    BchFile fileById = trainServiceImpl.getFileById(Integer.parseInt(fid));
                    bd.getMaintainList().get(0).setFileid( fileById.getId()+":"+fileById.getFilename()+":"+fileById.getFilepath());
                }
            }
        }
        return ResultModel.OK(brkdwnInfoById);
    }

    //故障修改 修改前先请求 /getBrkdwnInfoById
    @MyLog(value = "故障修改")
    @RequestMapping(value = "/updBrkdwnInfoById",method = RequestMethod.POST)
    public ResultModel updBrkdwnInfoById(@RequestBody BrkdwnVo bv){
        int i = brkdwnServiceImpl.updBrkdwnInfoById(bv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","更新成功");
        }else{
            map.put("msg","更新失败");
        }
        return ResultModel.OK(map);
    }

   //修改维修记录
    @MyLog(value = "修改维修记录")
    @RequestMapping(value = "/updMaintainInfoById",method = RequestMethod.POST)
    public ResultModel updMaintainInfoById(@RequestBody BrkdwnVo bv){
        int i = brkdwnServiceImpl.updMaintainInfoById(bv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","维修记录更新成功");
        }else{
            map.put("msg","维修记录更新失败");
        }
        return ResultModel.OK(map);
    }

    //故障删除
    @MyLog(value = "故障删除")
    @RequestMapping(value = "/delBrkdwnInfoBatch",method = RequestMethod.POST)
    public ResultModel delBrkdwnInfoBatch(@RequestBody BrkdwnVo bv){
        if(bv.getSelectids()==null || bv.getSelectids().length==0){
            return ResultModel.ERROR(ResultStatus.SELECTED_NOT_NULL);
        }
        if(MyStringUtils.isEmpty(bv.getPassword())){
            return  ResultModel.ERROR(ResultStatus.PASSWORD_NOT_NULL);
        }else {
            ResultStatus resultStatus = validOriginPwd(bv.getPassword());
            if(resultStatus.getCode()== -1017 || resultStatus.getCode()== -1100){
                return ResultModel.ERROR(resultStatus);
            }
        }
        Map<String,String> map=new HashMap<String, String>();
        int i=brkdwnServiceImpl.delBrkdwnInfoBatch(bv);
        if(i>0){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return ResultModel.OK(map);
    }

    //添加故障代码
    @MyLog(value = "添加故障代码")
    @RequestMapping(value = "/addBrkdwnCode",method = RequestMethod.POST)
    public ResultModel addBrkdwnCode(@RequestBody BrkdwnVo bv){
        if(MyStringUtils.isEmpty(bv.getBrkdwncode()) || MyStringUtils.isEmpty(bv.getBrkdwnname()) ||
                MyStringUtils.isEmpty(bv.getBrkdwnlvl()) || MyStringUtils.isEmpty(bv.getBrkdwntrntype())){
            return ResultModel.ERROR(ResultStatus.BRKDWNCODE_PARAM_NOT_NULL);
        }

        ResultStatus resultStatus = validBrkdwnCode(bv);
        if(resultStatus.getCode()== -3401 || resultStatus.getCode()== -3402 || resultStatus.getCode()== -3403){
            return ResultModel.ERROR(resultStatus);
        }
        int i = brkdwnServiceImpl.insertBrkdwnCode(bv);
        Map<String,String> map=new HashMap<String, String>();
        if(i>0){
            map.put("msg","添加故障代码成功");
        }else{
            map.put("msg","添加故障代码失败");
        }
        return ResultModel.OK(map);
    }

    //查询故障代码
    @RequestMapping(value = "/getBrkdwnCode",method = RequestMethod.POST)
    public ResultModel getBrkdwnCode(@RequestBody BrkdwnVo bv){
        if(bv.getPageNo()==null ){
            pageNo=1;
        }else{
            pageNo=bv.getPageNo();
        }
        if(bv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=bv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);
        List<BchBreakdownCode> bchBreakdownCodes = brkdwnServiceImpl.queryBrkdwnCode(bv);
        PageInfo<BchBreakdownCode> result=new PageInfo<>(bchBreakdownCodes);
        Map<String, Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }

    //故障代码导出excel
    @MyLog(value = "故障代码导出excel")
    @RequestMapping(value = "/brkdwncodeExportExcel",method = RequestMethod.POST)
    public void brkdwncodeExportExcel(@RequestBody BrkdwnVo bv, HttpServletResponse response){
        List<BchBreakdownCode> bchBreakdownCodes = brkdwnServiceImpl.queryBrkdwnCode(bv);
        if(!MyStringUtils.isEmpty(bv.getIflistItem())){
            //选了自定义导出列
            String[] headArray=null;
            List<String> list1=new ArrayList<>();
            List<String> list2=new ArrayList<>();
            List<Object[]> contentList=new ArrayList<>();
            if(bchBreakdownCodes.size()>0){
                for (int i=0;i<bchBreakdownCodes.size();i++){
                    BchBreakdownCode brkdwncode=bchBreakdownCodes.get(i);
                    if(!MyStringUtils.isEmpty(bv.getBrkdwncodeitem())){
                        if(i==0){
                            list1.add("故障代码");
                        }
                        list2.add(brkdwncode.getBrkdwncode());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnnameitem())){
                        if(i==0){
                            list1.add("故障名称");
                        }
                        list2.add(brkdwncode.getBrkdwnname());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnlvlitem())){
                        if(i==0){
                            list1.add("故障等级");
                        }
                        list2.add(getCateValue("btkdwnLvl",brkdwncode.getBrkdwnlvl()));
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwncauseitem())){
                        if(i==0){
                            list1.add("故障原因");
                        }
                        list2.add(brkdwncode.getBrkdwncause());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwntrntypeitem())){
                        if(i==0){
                            list1.add("车型");
                        }
                        list2.add(brkdwncode.getBrkdwntrntype());
                    }
                    if(!MyStringUtils.isEmpty(bv.getBrkdwnsysitem())){
                        if(i==0){
                            list1.add("故障系统");
                        }
                        list2.add(brkdwncode.getBrkdwnsys());
                    }
                    if(i==0){
                        if(list1!=null && list1.size()>0){
                            headArray=new String[list1.size()];
                            for (int k=0;k<list1.size();k++){
                                headArray[k]=String.valueOf(list1.get(k));
                                logger.info("选中数据列："+headArray[k]);
                            }
                        }
                    }
                    if(list2!=null && list2.size()>0){
                        Object[] obj=new String[list2.size()];
                        for (int j=0;j<list2.size();j++){
                            obj[j]=list2.get(j);
                            logger.info("选中列对应的值："+obj[j]);
                        }
                        contentList.add(obj);
                        list2.clear();
                    }
                }
            }
            try {
                ExcelUtil.ExportExcel(response,headArray,contentList,"故障代码列表");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            String[] headArray={"故障代码","故障名称","故障等级","故障原因","车型","故障系统"};
            List<Object[]> contentList=new ArrayList<>();
            if(bchBreakdownCodes!=null && bchBreakdownCodes.size()>0){
                for (BchBreakdownCode item:bchBreakdownCodes ) {
                    Object[] obj={item.getBrkdwncode(),item.getBrkdwnname(),getCateValue("btkdwnLvl",item.getBrkdwnlvl()),
                         item.getBrkdwncause(),item.getBrkdwntrntype(),item.getBrkdwnsys()};
                    contentList.add(obj);
                }
            }
            try {
                ExcelUtil.ExportExcel(response,headArray,contentList,"故障代码列表");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //故障代码excel导入数据
    @MyLog(value = "故障代码excel导入")
    @RequestMapping(value = "/brkdwncodeImportExcel",method = RequestMethod.POST)
    public ResultModel brkdwncodeImportExcel(@RequestParam("file") MultipartFile file){
        int j=0;
        Map<String,String> map=new HashMap<>();
        try {
            String[][] values = ExcelUtil.importExcel(file,"01");
            for (int i = 0; i < values.length; i++) {

                String[] row = values[i];
                if(row.length<6){
                   return ResultModel.OK(values);
                }
                BrkdwnVo bv=new BrkdwnVo();
                if(MyStringUtils.isEmpty(row[0]) || MyStringUtils.isEmpty(row[1]) ||
                        MyStringUtils.isEmpty(row[2]) || MyStringUtils.isEmpty(row[4])){
                    map.put("msg"+(i+1),"第"+(i+1)+"行故障代码、故障名称、故障等级、车型有空字段");
                    continue;
                }
                if(!MyStringUtils.isEmpty(row[0]) && !MyStringUtils.validBrkdwnCode(row[0])){
                    map.put("msg"+(i+1),"第"+(i+1)+"行故障代码必须为6位字母数字的组合");
                    continue;
                }

                if(!MyStringUtils.isEmpty(row[1]) && !MyStringUtils.validRoledesc(row[1])){
                    map.put("msg"+(i+1),"第"+(i+1)+"行故障名称最多为200汉字");
                    continue;
                }
                if(!MyStringUtils.isEmpty(row[3]) && !MyStringUtils.validRoledesc(row[3])){
                    map.put("msg"+(i+1),"第"+(i+1)+"行故障原因最多为200汉字");
                    continue;
                }
                bv.setBrkdwncode(row[0]);//故障代码
                bv.setBrkdwnname(row[1]);//故障名称
                bv.setBrkdwnlvl(row[2]);//故障等级
                bv.setBrkdwncause(row[3]);//故障原因
                bv.setBrkdwntrntype(row[4]);//车型
                bv.setBrkdwnsys(row[5]);//故障系统
               j= brkdwnServiceImpl.insertBrkdwnCode(bv);
               j++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(j>0){
           map.put("msg","导入成功");
        }else{
            map.put("msg","导入失败");
        }
        return ResultModel.OK(map);
    }

    @RequestMapping(value = "/getBrkdwnCodeById",method = RequestMethod.POST)
    public ResultModel getBrkdwnCodeById(@RequestBody BrkdwnVo bv){
       if(bv.getId()==null){
           Map<String,String> map=new HashMap<String, String>();
           map.put("msg","参数id必输");
           return ResultModel.OK(map);
       }
        BchBreakdownCode bchBreakdownCode = brkdwnServiceImpl.queryBrkdwnCodeById(bv);
        return ResultModel.OK(bchBreakdownCode);
    }

    @MyLog(value = "故障代码修改")
    @RequestMapping(value = "/updateBrkdwnCode",method = RequestMethod.POST)
    public ResultModel updateBrkdwnCode(@RequestBody BrkdwnVo bv){
        Map<String,String> map=new HashMap<String, String>();
       if(bv.getId()==null){
           map.put("msg","参数id必输");
           return ResultModel.OK(map);
       }
        if(MyStringUtils.isEmpty(bv.getBrkdwncode()) && MyStringUtils.isEmpty(bv.getBrkdwnname()) && MyStringUtils.isEmpty(bv.getBrkdwnlvl()) &&
                 MyStringUtils.isEmpty(bv.getBrkdwntrntype()) && MyStringUtils.isEmpty(bv.getBrkdwnsys()) && MyStringUtils.isEmpty(bv.getBrkdwncause())){
            //一项未修改
            return ResultModel.OK();
        }
        ResultStatus resultStatus = validBrkdwnCode(bv);
        if(resultStatus.getCode()== -3401 || resultStatus.getCode()== -3402 || resultStatus.getCode()== -3403){
            return ResultModel.ERROR(resultStatus);
        }
        int i = brkdwnServiceImpl.updBrkdwnCode(bv);
        if(i>0){
            map.put("msg","修改故障代码成功");
        }else{
            map.put("msg","修改故障代码失败");
        }
        return ResultModel.OK(map);
    }

    @MyLog(value = "故障代码删除")
    @RequestMapping(value = "/deleteBrkdwnCode",method = RequestMethod.POST)
    public ResultModel deleteBrkdwnCode(@RequestBody BrkdwnVo bv){
        Map<String,String> map=new HashMap<String, String>();
        if(bv.getId()==null && (bv.getSelectids()==null || bv.getSelectids().length==0)){
            return ResultModel.ERROR(ResultStatus.SELECTED_NOT_NULL);
        }
        if(MyStringUtils.isEmpty(bv.getPassword())){
            return  ResultModel.ERROR(ResultStatus.PASSWORD_NOT_NULL);
        }else {
            ResultStatus resultStatus = validOriginPwd(bv.getPassword());
            if(resultStatus.getCode()== -1017 ){
                return ResultModel.ERROR(resultStatus);
            }
        }
        int i = brkdwnServiceImpl.delBrkdwnCode(bv);
        if(i>0){
            map.put("msg","删除成功");
        }else{
            map.put("msg","删除失败");
        }
        return ResultModel.OK(map);
   }

    public List<BchBreakdownInfo> getBrkdwnInfoById(int id){
       return brkdwnServiceImpl.getBrkdwnInfoById(id);
    }
    //故障代码条件校验
    public ResultStatus validBrkdwnCode(BrkdwnVo bv){
        if(!MyStringUtils.isEmpty(bv.getBrkdwncode()) && !MyStringUtils.validBrkdwnCode(bv.getBrkdwncode())){
            return ResultStatus.BRKDWNCODE_CODE_CHECKED;
        }
       /* if(!MyStringUtils.isEmpty(bv.getBrkdwnname()) && !MyStringUtils.validBrkdwnname(bv.getBrkdwnname())){
            return ResultStatus.BRKDWNCODE_NAME_CHECEKED;
        }
        if(!MyStringUtils.isEmpty(bv.getBrkdwncause()) && !MyStringUtils.validBrkdwnname(bv.getBrkdwncause())){
            return ResultStatus.BRKDWNCODE_CAUSE_CHECKED;
        }*/
        if(!MyStringUtils.isEmpty(bv.getBrkdwnname()) && !MyStringUtils.validRoledesc(bv.getBrkdwnname())){
            return ResultStatus.BRKDWNCODE_NAME_CHECEKED;
        }
        if(!MyStringUtils.isEmpty(bv.getBrkdwncause()) && !MyStringUtils.validRoledesc(bv.getBrkdwncause())){
            return ResultStatus.BRKDWNCODE_CAUSE_CHECKED;
        }
      return ResultStatus.SUCCESS;
    }

    //查询码值
    public String getCateValue(String catename,String catevalue){
        return trainServiceImpl.queryCateValue(catename,catevalue);
    }
}
