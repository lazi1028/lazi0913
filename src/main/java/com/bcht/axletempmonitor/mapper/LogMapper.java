package com.bcht.axletempmonitor.mapper;

import com.bcht.axletempmonitor.pojo.BchLogtrace;
import com.bcht.axletempmonitor.vo.LogVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    public int insertLog(BchLogtrace log);//插入日志
    public List<BchLogtrace> queryLog(LogVo lv);//查询日志
}
