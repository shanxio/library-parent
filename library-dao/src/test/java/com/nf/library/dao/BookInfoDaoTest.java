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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public void bookInfoUpdate() throws Exception {
        BookInfo bookInfo = getBookInfo();
        bookInfoDao.bookInfoUpdate(bookInfo);
    }

    @Test
    public void booknfoGetAll() throws Exception {

       List<BookInfo> bookInfos = bookInfoDao.getAll(new BookInfo(),1,10);
        for (BookInfo bookInfo : bookInfos) {
            System.out.println("------------"+bookInfo);
        }
    }
    private BookInfo getBookInfo() throws ParseException {
        BookInfo bookInfo = new BookInfo() ;
//        bookInfo.setId(9);
//        bookInfo.setIsbn("123213");
        bookInfo.setBookName("java");
//        bookInfo.setBookAuthor("123");
//        bookInfo.setBookType("学习");
//        bookInfo.setBookPublish("机械出版社");
//        bookInfo.setBookPrice(new BigDecimal("123"));
//        bookInfo.setTmamount(1);
//        bookInfo.setBookStock(1);
//        bookInfo.setBookState(0);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date stateDate = simpleDateFormat.parse("2019-01-01");
//        bookInfo.setStateDate(stateDate);
//
//        Date endDate = simpleDateFormat.parse("2019-01-10");
//        bookInfo.setEndDate(endDate);
        return bookInfo;
    }

    ;
}
