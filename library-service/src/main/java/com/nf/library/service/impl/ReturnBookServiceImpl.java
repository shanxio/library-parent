package com.nf.library.service.impl;

import com.nf.library.dao.ReturnBookDao;
import com.nf.library.entity.ReturnBook;
import com.nf.library.service.ReturnBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnBookServiceImpl implements ReturnBookService {
    @Autowired
    private ReturnBookDao returnBookDao;

    @Override
    public void returnBookInsert(ReturnBook returnBook) {
        returnBookDao.returnBookInsert(returnBook);
    }

    @Override
    public void returnBookDelete(Integer id) {
        returnBookDao.returnBookDelete(id);
    }

    @Override
    public void returnBookBatchDelete(Integer[] ids) {
        returnBookDao.returnBookBatchDelete(ids);
    }

    @Override
    public List<ReturnBook> getAll(ReturnBook returnBook, int pageNum, int pageSize) {
        return returnBookDao.getAll( returnBook,  pageNum,  pageSize) ;
    }
}
