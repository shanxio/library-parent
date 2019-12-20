package com.nf.library.dao;

import com.nf.library.dao.config.DaoConfig;
import com.nf.library.entity.ReaderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class ReaderInfoDaoTest {

    @Autowired
    private ReaderInfoDao readerInfoDao;

    @Test
    public void readerInfoQuery(){
        ReaderInfo readerInfo = new ReaderInfo();
        readerInfo.setReaderName("12");
        List<ReaderInfo> lists = readerInfoDao.getAll(readerInfo,1,2);
        for (ReaderInfo list : lists) {
            System.out.println(list);
        }
    }
}
