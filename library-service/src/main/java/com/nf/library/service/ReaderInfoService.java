package com.nf.library.service;

import com.nf.library.entity.ReaderInfo;

/**
 * 读者的业务
 * @author Sam
 */
public interface ReaderInfoService {
    /**
     * 根据读者id查询信息
     * @param id
     * @return
     */
    ReaderInfo getById(Integer id);
}
