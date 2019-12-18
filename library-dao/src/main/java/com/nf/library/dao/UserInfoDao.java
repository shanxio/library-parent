package com.nf.library.dao;

import com.nf.library.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用于用户表操作
 * @author Sam
 */
public interface UserInfoDao {
    /**
     * 查询所有的用户信息
     * @return
     */
    List<UserInfo> getAll(@Param("user") UserInfo userInfo,
                          @Param("pageNum")int pageNum,
                          @Param("pageSize")int pageSize);
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
