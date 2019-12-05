package com.nf.library.dao;

import com.nf.library.dao.config.DaoConfig;
import com.nf.library.entity.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * 图书信息的crud操作
 * @author Sam
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class BookInfoDaoTest {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Test
    public void  bookInfoInsertTest(){
        BookInfo bookInfo = new BookInfo() ;
        bookInfo.setIsbn("123213");
        bookInfo.setBookName("java多线程");
        bookInfo.setBookAuthor("123");
        bookInfo.setBookType("学习");
        bookInfo.setBookPublish("机械出版社");
        bookInfo.setBookPrice(new BigDecimal("123"));
        bookInfo.setTmamount(1);
        bookInfo.setBookStock(1);
        bookInfo.setBookState(1);
        bookInfoDao.bookInfoInsert(bookInfo);
    };
}
