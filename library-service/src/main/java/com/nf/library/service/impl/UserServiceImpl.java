package com.nf.library.service.impl;

import com.nf.library.dao.UserDao;
import com.nf.library.entity.User;
import com.nf.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * userServie实现类
 * @author Sam
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getByUserName(String userName){

        return userDao.getByUserName(userName);
    }

    @Override
    public User getByloginName(String loginName) {

        return userDao.getByloginName(loginName);
    }


    @Transactional
    @Override
    public void deleteById(Integer id) {

        userDao.deleteById(id);
    }

    @Override
    public User getByloginNameAndPassword(String loginName, String password) {
        return userDao.getByloginNameAndPassword(loginName,password);
    }

}
