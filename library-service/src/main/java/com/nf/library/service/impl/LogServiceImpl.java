package com.nf.library.service.impl;

import com.nf.library.dao.LogDao;
import com.nf.library.entity.Log;
import com.nf.library.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志具体实现类
 * @author Sam
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;
    @Override
    public List<Log> getAll(int pageNum, int pageSize) {
        return logDao.getAll(pageNum,pageSize);
    }

    @Override
    public void insert(Log log) {
        logDao.insert(log);
    }
}
