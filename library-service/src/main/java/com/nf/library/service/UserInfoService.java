package com.nf.library.service;

import com.nf.library.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    UserInfo getByRealName(String realName);


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
    void userInfoByIdDelete(Integer userId);


    /**
     * 登录请求输入密码与登录名
     * @param username
     * @return
     */
    UserInfo getByUsernameAndPassword(String username,  String password);

    /**
     * 查询所有的用户信息
     * @return
     */
    List<UserInfo> getAll(@Param("user") UserInfo userInfo,
                          @Param("pageNum")int pageNum,
                          @Param("pageSize")int pageSize);


    /**
     * 添加用户信息
     * @param userInfo
     */
    void userInfoInsert(UserInfo userInfo);


    /**
     * 批量删除数据
     * @param id
     */
    void userInfoBatchDelete(Integer[] id);


    /**
     * 修改数据
     * @param userInfo
     */
    void userInfoUpdate(UserInfo userInfo);
}
