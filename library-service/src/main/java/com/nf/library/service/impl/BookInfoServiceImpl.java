package com.nf.library.service.impl;

import com.nf.library.dao.BookInfoDao;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图书信息实习类，负责实习所有的图书信息业务
 * @author Sam
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {
    @Autowired
    private BookInfoDao bookInfoDao;
    @Override
    public List<BookInfo> getAll(int pageNum,int pageSize) {

        return bookInfoDao.getAll(new BookInfo(),pageNum,pageSize);
    }

    @Override
    public List<BookInfo> getSearchAll(BookInfo bookInfo, int pageNum, int pageSize) {
        return bookInfoDao.getAll(bookInfo,pageNum,pageSize);
    }

    @Override
    public BookInfo getByIsbn(String isbn) {
        return bookInfoDao.getByIsbn(isbn);
    }

    @Override
    public void bookInfoInsert(BookInfo bookInfo) {
        bookInfoDao.bookInfoInsert(bookInfo);
    }

    @Override
    public void bookInfoByIdDelete(String id) throws AppException {
        bookInfoDao.bookInfoByIdDelete(id);
    }

    @Override
    public void bookInfoUpdate(BookInfo bookInfo) {

        bookInfoDao.bookInfoUpdate(bookInfo);
    }

    @Override
    public void bookInfoBatchDelete(String[] isbns) {
        bookInfoDao.bookInfoBatchDelete(isbns);
    }
}
