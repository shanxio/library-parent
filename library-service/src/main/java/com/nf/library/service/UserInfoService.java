package com.nf.library.service;

import com.nf.library.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户的业务接口
 * @author Sam
 */
public interface UserInfoService {

    /**
     * 根据姓名查找用户
     * @param realName
     * @return
     */
    UserInfo getByRealName( String realName);


    /**
     * 根据登录名查找用户
     * @param username
     * @return
     */
    UserInfo getByUsername(String username);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteById(Integer userId);


    /**
     * 登录请求输入密码与登录名
     * @param username
     * @return
     */
    UserInfo getByUsernameAndPassword(String username,  String password);
}
