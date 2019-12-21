package com.nf.library.service.impl;

import com.nf.library.dao.ReaderInfoDao;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<ReaderInfo> getAll(ReaderInfo readerInfo, int pageNum, int pageSize) {
        return readerInfoDao.getAll(readerInfo,pageNum,pageSize);
    }

    @Override
    public void readerInfoBatchDelete(Integer[] id) {
        readerInfoDao.readerInfoBatchDelete(id);
    }

    @Override
    public void readerInfoDelete(Integer id) {
        readerInfoDao.readerInfoDelete(id);
    }

    @Override
    public void readerInfoUpdate(ReaderInfo readerInfo) {
        readerInfoDao.readerInfoUpdate(readerInfo);
    }

    @Override
    public void readerInfoInsert(ReaderInfo readerInfo) {
        readerInfoDao.readerInfoInsert(readerInfo);
    }

    @Override
    public void readerInfoBatchInsert(List<ReaderInfo> readerInfos) {
        readerInfoDao.readerInfoBatchInsert(readerInfos);
    }
}
