package com.nf.library.dao;

import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
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
    List<BookInfo> getAll(@Param("pageNum")int pageNum, @Param("pageSize") int pageSize);


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
    void bookInfoDeleteById(Integer id) ;

    /**
     * 修改
     * @param bookInfo
     */
    void bookInfoUpdate(BookInfo bookInfo);
}
