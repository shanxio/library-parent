package com.nf.library.dao;


import com.nf.library.entity.BookType;

import java.util.List;

public interface BookTypeDao {
    /**
     * 查询所有的分类信息
     * @return
     */
    List<BookType> getAll();
}
