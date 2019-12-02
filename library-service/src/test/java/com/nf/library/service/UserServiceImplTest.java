package com.nf.library.service;

import com.nf.library.service.config.ServiceConfig;
import com.nf.library.entity.User;
import com.nf.library.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;


    @Test
    public void getByloginNameAndPasswordTest()  {
        User user = userService.getByloginNameAndPassword("admin","123456");
        System.out.println(user);
    }
}