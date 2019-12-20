package com.nf.library.dao;

import com.nf.library.entity.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志操作类
 * @author Sam
 */
public interface LogDao {
    /**
     * 查询所有的日志
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Log> getAll(@Param("pageNum") int pageNum,@Param("pageSize")int pageSize);

    /**
     * 添加日志信息
     * @param log
     */
    void insert(Log log);
}
