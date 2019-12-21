package com.nf.library.service;

import com.nf.library.entity.ReturnBook;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sam
 */
@Service
public interface ReturnBookService {


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
    List<ReturnBook> getAll(ReturnBook returnBook,
                            int pageNum,
                            int pageSize);
}
