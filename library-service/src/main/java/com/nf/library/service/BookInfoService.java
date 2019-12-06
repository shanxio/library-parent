package com.nf.library.service;

import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书信息业务类
 * @author Sam
 */
public interface BookInfoService  {
    /**
     * 查询所有的图书信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<BookInfo> getAll(int pageNum, int pageSize);


    /**
     * 添加图书
     * @param bookInfo
     */
    void bookInfoInsert(BookInfo bookInfo);

    /**
     * 根据id删除对应的图书信息
     * @param id
     * @throws AppException
     */
    void bookInfoDeleteById(Integer id);

    /**
     *批量删除书籍
     * @param isbns
     */
    void bookInfoDeleteBatch(String[] isbns);
}
