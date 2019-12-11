package com.nf.library.dao;

import com.nf.library.entity.ReaderInfo;

/**
 * 读者信息的相关操作
 * @author Sam
 */
public interface ReaderInfoDao {
    /**
     * 根据读者id查询信息
     * @param id
     * @return
     */
    ReaderInfo getById(Integer id);
}
