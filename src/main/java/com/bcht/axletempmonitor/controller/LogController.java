package com.bcht.axletempmonitor.controller;

import com.bcht.axletempmonitor.pojo.BchLogtrace;
import com.bcht.axletempmonitor.service.ILogService;
import com.bcht.axletempmonitor.utils.DateUtils;
import com.bcht.axletempmonitor.utils.MyStringUtils;
import com.bcht.axletempmonitor.utils.ResultModel;
import com.bcht.axletempmonitor.utils.ResultStatus;
import com.bcht.axletempmonitor.vo.LogVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static com.bcht.axletempmonitor.utils.OtherUtils.pageHelperResult;

@RestController
@RequestMapping(value = "/log")
public class LogController {

    @Autowired
    ILogService logServiceImpl;
    private Integer pageNo;
    private Integer pageSize;

    @RequestMapping(value = "/getLog",method = RequestMethod.POST)
    public ResultModel getLog(@RequestBody LogVo lv){
        if(!MyStringUtils.isEmpty(lv.getStarttime()) && !MyStringUtils.isEmpty(lv.getEndtime())){
            if(DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",lv.getStarttime()).getTime() > DateUtils.parseDate("yyyy-MM-dd HH:mm:ss",lv.getEndtime()).getTime()){
               return ResultModel.ERROR(ResultStatus.STARTTIME_ENDTIME_COMPARE);
            }
        }
        if(lv.getPageNo()==null ){
            pageNo=1;
        }else{
            pageNo=lv.getPageNo();
        }
        if(lv.getPageSize()==null){
            pageSize=50;
        }else{
            pageSize=lv.getPageSize();
        }
        PageHelper.startPage(pageNo,pageSize);
        List<BchLogtrace> bchLogtraces = logServiceImpl.queryLog(lv);
        PageInfo<BchLogtrace> result=new PageInfo<>(bchLogtraces);
        Map<String, Object> map = pageHelperResult(result);
        return ResultModel.OK(map);
    }
}
