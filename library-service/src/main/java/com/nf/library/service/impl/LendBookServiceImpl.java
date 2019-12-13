package com.nf.library.service.impl;

import com.nf.library.dao.BookInfoDao;
import com.nf.library.dao.LendBookDao;
import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.service.LendBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sam
 */
@Service
public class LendBookServiceImpl implements LendBookService {
    @Autowired
    private LendBookDao lendBookDao;
    @Autowired
    private BookInfoDao bookInfoDao;
    @Override
    @Transactional(readOnly = false)
    public void lendBookInsert(ReaderInfo readerInfo, BookInfo bookInfo) {
        BookInfo bookInfo1 = new BookInfo();
        //现存册数减一
        bookInfo1.setBookStock(bookInfoDao.getByIsbn(bookInfo.getIsbn()).getBookStock()-1);
        bookInfo1.setIsbn(bookInfo.getIsbn());
        bookInfoDao.bookInfoUpdate(bookInfo1);
        lendBookDao.lendBookInsert(readerInfo,bookInfo);
    }

    @Override
    public List<LendBook> getAll(LendBook lendBook, Integer pageNum, Integer pageSize) {
        return lendBookDao.getAll(lendBook,pageNum,pageSize);
    }

    @Override
    public void lendBookBatchDelete(Integer[] id) {
        lendBookDao.lendBookBatchDelete(id);
    }

    @Override
    public void lendBookDelete(Integer id) {
        lendBookDao.lendBookBatchDelete(new Integer[]{id});
    }

    @Override
    public void lendBookUpdate(LendBook lendBook) {
        lendBookDao.lendBookUpdate(lendBook);
    }

    @Override
    @Transactional(readOnly = false)
    public void lendBookStateUpdate(LendBook lendBook) {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setIsbn(lendBook.getIsbn());
        bookInfo.setBookStock(bookInfoDao.getByIsbn(lendBook.getIsbn()).getBookStock()+1);
        //图书的现存数量加一
        bookInfoDao.bookInfoUpdate(bookInfo);
        lendBookDao.lendBookUpdate(lendBook);
    }
    @Transactional(readOnly = false)
    @Override
    public void lendTotalcountUpdate(Integer id) {
        LendBook lendBook = new LendBook();
        lendBook.setId(id);
        lendBook.setTotalcount(lendBookDao.getById(id).getTotalcount()+1);
        lendBookDao.lendBookUpdate(lendBook);
    }


}
