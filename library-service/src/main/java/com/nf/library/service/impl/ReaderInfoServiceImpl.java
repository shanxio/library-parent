package com.nf.library.service.impl;

import com.nf.library.dao.ReaderInfoDao;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sam
 */
@Service
public class ReaderInfoServiceImpl implements ReaderInfoService {

    @Autowired
    private ReaderInfoDao readerInfoDao;

    @Override
    public ReaderInfo getById(Integer id) {
        return readerInfoDao.getById(id);
    }
}
