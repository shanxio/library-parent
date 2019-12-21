package com.nf.library.dao;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借阅信息表操作
 * @author Sam
 */
public interface LendBookDao {
    /**
     * 录入读者的借阅信息
     * @param lendBook
     */
    void lendBookInsert(LendBook lendBook);

    /**
     * 根据条件查询数据
     * @param lendBook
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<LendBook> getAll(@Param("lend")LendBook lendBook,
                          @Param("pageNum")Integer pageNum,
                          @Param("pageSize")Integer pageSize);

    /**
     * 根据id查询借阅信息
     * @param id
     * @return
     */
    LendBook getById(Integer id);
    /**
     * 批量删除数据
     * @param id
     */
    void lendBookBatchDelete(Integer[] id);

    /**
     * 修改借阅表信息
     * @param lendBook
     */
    void lendBookUpdate(@Param("lend") LendBook lendBook);

    /**
     * 删除数据
     * @param id
     */
    void lendBookDelete(Integer id);

}
