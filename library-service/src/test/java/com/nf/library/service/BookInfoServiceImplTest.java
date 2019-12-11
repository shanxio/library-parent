package com.nf.library.service;

import com.nf.library.dao.BookInfoDao;
import com.nf.library.entity.BookInfo;
import com.nf.library.execption.AppException;
import com.nf.library.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class BookInfoServiceImplTest {
    @Autowired
    private BookInfoService bookInfoService;
    @Test
    public void bookInfoDeleteById()  {
        try{

            bookInfoService.bookInfoByIdDelete(44);
        }catch (RuntimeException e){
            System.out.println(e.getMessage()+"-----");
        }

    }

    @Test
    public void getAll()  {
        bookInfoService.getAll(1,2);

    }

}
