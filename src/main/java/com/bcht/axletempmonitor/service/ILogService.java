package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.pojo.BchLogtrace;
import com.bcht.axletempmonitor.vo.LogVo;

import java.util.List;

public interface ILogService {
    public int insertLog(BchLogtrace log);
    public List<BchLogtrace> queryLog(LogVo lv);
}
