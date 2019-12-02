package com.nf.library.dao;

import com.nf.library.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用于用户表操作
 * @author Sam
 */
public interface UserDao {

    /**
     * 根据姓名查找用户
     * @param userName
     * @return
     */
    User getByUserName(@Param("username") String userName);


    /**
     * 根据登录名查找用户
     * @param loginName
     * @return
     */
    User getByloginName(@Param("loginName") String loginName);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteById(Integer id);


    /**
     * 登录请求输入密码与登录名
     * @param loginName
     * @return
     */
    User getByloginNameAndPassword(@Param("loginName") String loginName,@Param("password") String password);

}
