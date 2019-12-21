package com.nf.library.service;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.LendBook;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class LendBookServiceTest {

    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private LendBookService lendBookService;

    /**
     * 测试增加
     */



    @Test
    public void lendBookGetAllTest(){
        LendBook lendBook = new LendBook();
        //lendBook.setLendState(1);
        List<LendBook> lendBooks = lendBookService.getAll(lendBook,1,10);
        for (LendBook book : lendBooks) {
            System.out.println("book = " + book);
        }
    }
}
