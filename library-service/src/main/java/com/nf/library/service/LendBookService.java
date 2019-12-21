package com.nf.library.service;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.execption.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 借阅信息的业务
 * @author Sam
 */
public interface LendBookService {

    /**
     * 录入读者的借阅信息
     * @param lendBook
     */
    ResponseVo lendBookInsert(LendBook lendBook);

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
    void lendBookUpdate(LendBook lendBook);

    /**
     * 删除数据
     * @param id
     */
    void lendBookDelete(Integer id);

    /**
     *归还书籍
     * @param lendBook
     */
    void returnBook(LendBook lendBook);

    /**
     * 修改续借天数
     * @param lendBook
     */
    ResponseVo lendBookTotalUpdate(LendBook lendBook);
}
