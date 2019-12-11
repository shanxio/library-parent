package com.nf.library.dao;

import com.nf.library.security.process.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用于用户表操作
 * @author Sam
 */
public interface UserInfoDao {

    /**
     * 根据姓名查找用户
     * @param realName
     * @return
     */
    UserInfo getByRealName(@Param("realName") String realName);


    /**
     * 根据登录名查找用户
     * @param username
     * @return
     */
    UserInfo getByUsername(@Param("username") String username);

    /**
     * 根据id删除用户
     * @param userId
     */
    void userInfoByIdDelete(Integer userId);


    /**
     * 登录请求输入密码与登录名
     * @param username
     * @return
     */
    UserInfo getByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
