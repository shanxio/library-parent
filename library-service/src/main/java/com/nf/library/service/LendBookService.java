package com.nf.library.service;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借阅信息的业务
 * @author Sam
 */
public interface LendBookService {
    /**
     * 录入读者的借阅信息
     * @param readerInfo
     * @param bookInfo
     */
    void lendBookInsert(@Param("reader") ReaderInfo readerInfo, @Param("book") BookInfo bookInfo);

    /**
     * 根据条件查询数据
     * @param lendBook
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<LendBook> getAll(LendBook lendBook,
                          Integer pageNum,
                          Integer pageSize);

    /**
     * 批量删除数据
     * @param id
     */
    void lendBookBatchDelete(Integer[] id);

    /**
     * 根据id删除数据
     * @param id
     */
    void lendBookDelete(Integer id);



    /**
     * 修改借阅表信息
     * @param lendBook
     */
    void lendBookUpdate(LendBook lendBook);

    /**
     * 修改借阅状态
     * @param lendBook
     */
    void lendBookStateUpdate(LendBook lendBook);

    /**
     * 修改续借状态
     * @param id
     */
    void lendTotalcountUpdate(Integer id);
}
