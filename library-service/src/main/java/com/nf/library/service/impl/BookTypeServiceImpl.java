package com.nf.library.service.impl;

import com.nf.library.dao.BookTypeDao;
import com.nf.library.entity.BookType;
import com.nf.library.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Autowired
    private BookTypeDao bookTypeDao;
    @Override
    public List<BookType> getAll() {
        return bookTypeDao.getAll();
    }
}
