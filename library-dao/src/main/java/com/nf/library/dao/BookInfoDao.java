package com.nf.library.dao;

import com.nf.library.entity.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图书信息的crud操作
 * @author Sam
 */
public interface BookInfoDao {
    /**
     * 查询所有的图书信息
     * @return
     */
    List<BookInfo> getAll(@Param("bookInfo") BookInfo bookInfo,@Param("pageNum")int pageNum, @Param("pageSize") int pageSize);

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
     * @throws AppException
     */
    void bookInfoByIdDelete(Integer id) ;

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
