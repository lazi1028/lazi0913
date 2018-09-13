package com.bcht.axletempmonitor.service;

import com.bcht.axletempmonitor.mapper.LogMapper;
import com.bcht.axletempmonitor.pojo.BchLogtrace;
import com.bcht.axletempmonitor.vo.LogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements ILogService {
    @Autowired
    LogMapper logMapper;

    @Override
    public int insertLog(BchLogtrace log) {
        return logMapper.insertLog(log);
    }

    @Override
    public List<BchLogtrace> queryLog(LogVo lv) {
        return logMapper.queryLog(lv);
    }
}
