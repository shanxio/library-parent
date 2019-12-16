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
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<BookInfo> getAll(int pageNum, int pageSize);

    /**
     * 根据条件查询图书信息
     * @param bookInfo
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<BookInfo> getSearchAll(BookInfo bookInfo,int pageNum,int pageSize);

    /**
     * 根据isbn查询图书信息
     * @param isbn
     * @return
     */
    BookInfo getByIsbn(String isbn);
    /**
     * 添加图书
     * @param bookInfo
     */
    void bookInfoInsert(BookInfo bookInfo);

    /**
     * 根据id删除对应的图书信息
     * @param id
     */
    void bookInfoByIdDelete(Integer id);
    /**
     * 修改
     * @param bookInfo
     */
    void bookInfoUpdate(BookInfo bookInfo);
    /**
     *批量删除书籍
     * @param isbns
     */
    void bookInfoBatchDelete(String[] isbns);
}
