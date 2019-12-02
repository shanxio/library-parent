package com.nf.library.service;

import com.nf.library.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Sam
 */
public interface UserService {

    /**
     * 根据姓名查找用户
     * @param userName
     * @return
     */
    User getByUserName(String userName);


    /**
     * 根据登录名查找用户
     * @param loginName
     * @return
     */
    User getByloginName( String loginName);



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
    User getByloginNameAndPassword( String loginName,  String password);
}
