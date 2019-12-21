package com.nf.library.dao;


import com.nf.library.entity.ReturnBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Sam
 */
public interface ReturnBookDao {

    /**
     * 添加归还记录
     * @param returnBook
     */
    void returnBookInsert(ReturnBook returnBook);

    /**
     * 根据id删除归还记录
     * @param id
     */
    void returnBookDelete(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void returnBookBatchDelete(Integer[] ids);

    /**
     * 查询所有的归还记录
     * @return
     */
    List<ReturnBook> getAll(@Param("returnBook")ReturnBook returnBook,
                            @Param("pageNum")int pageNum,
                            @Param("pageSize")int pageSize);
}
