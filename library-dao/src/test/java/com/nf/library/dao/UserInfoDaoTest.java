package com.nf.library.dao;


import com.nf.library.dao.config.DaoConfig;
import com.nf.library.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class UserInfoDaoTest {

    @Autowired
    private UserInfoDao userInfoDao;
    @Test
    public void userDaoTestQuery()  {
        UserInfo userInfo = userInfoDao.getByUsername("admin");
        System.out.println(userInfo.isEnabled());
    }
}
