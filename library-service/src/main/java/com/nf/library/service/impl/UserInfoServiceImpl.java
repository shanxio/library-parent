package com.nf.library.service.impl;

import com.nf.library.dao.UserInfoDao;
import com.nf.library.entity.UserInfo;
import com.nf.library.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * userServie实现类
 * @author Sam
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public UserInfo getByRealName(String realName) {
        return userInfoDao.getByRealName(realName);
    }

    @Override
    public UserInfo getByUsername(String username) {
        return userInfoDao.getByRealName(username);
    }

    @Override
    public void userInfoByIdDelete(Integer userId) {
        userInfoDao.userInfoByIdDelete(userId);
    }

    @Override
    public UserInfo getByUsernameAndPassword(String username, String password) {
        return userInfoDao.getByUsernameAndPassword(username,password);
    }

    @Override
    public List<UserInfo> getAll(UserInfo userInfo, int pageNum, int pageSize) {
        return userInfoDao.getAll(userInfo,pageNum,pageSize);
    }

    @Override
    public void userInfoInsert(UserInfo userInfo) {
        userInfoDao.userInfoInsert(userInfo);
    }

    @Override
    public void userInfoBatchDelete(Integer[] id) {
        userInfoDao.userInfoBatchDelete(id);
    }

    @Override
    public void userInfoUpdate(UserInfo userInfo) {
        userInfoDao.userInfoUpdate(userInfo);
    }
}
