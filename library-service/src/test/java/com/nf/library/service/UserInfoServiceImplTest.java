package com.nf.library.service;

import com.nf.library.entity.UserInfo;
import com.nf.library.service.aop.LogAspect;
import com.nf.library.service.config.ServiceConfig;
import com.nf.library.service.impl.UserDetailsServiceImpl;
import com.nf.library.service.impl.UserInfoServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class UserInfoServiceImplTest {

    @Autowired
    private UserInfoServiceImpl userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public LogAspect logAspect;
    @Test
    public void getByloginNameAndPasswordTest()  {

        UserInfo userInfo = userService.getByUsername("admin");
        System.out.println(userInfo);
    }


}