package com.nf.library.dao;


import com.nf.library.dao.config.DaoConfig;
import com.nf.library.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;
    @Test
    public void userDaoTestQuery()  {
        List<UserInfo> userInfos = userInfoDao.getAll(new UserInfo(),1,100);

        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }
    }
    @Test
    public void userDaoTestInsert()  {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("12321");
        int user = userInfoDao.userInfoInsert(userInfo);
        System.out.println(userInfo.getUserId());
//        System.out.println(user.getUserId());
    }


}
