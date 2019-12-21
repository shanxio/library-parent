package com.nf.library.service;

import com.nf.library.entity.BookType;

import java.util.List;

public interface BookTypeService {
    /**
     * 查询所有的分类信息
     * @return
     */
    List<BookType> getAll();
}
