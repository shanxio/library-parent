package com.nf.library.service;

import com.nf.library.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书信息业务类
 * @author Sam
 */
public interface BookInfoService  {
    /**
     * 查询所有的图书信息
     * @return
     */
    List<BookInfo> getAll(int pageNum, int pageSize);


    /**
     * 添加图书
     * @param bookInfo
     */
    void bookInfoInsert(BookInfo bookInfo);
}
