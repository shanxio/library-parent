package com.nf.library.dao;

import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 关于用户角色相关操作
 * @author Sam
 */
public interface RoleInfoDao {
    /**
     * 根据登录用户查找对应的角色
     * @param username
     * @return
     */
    List<RoleInfo> getRoleByUsername(@Param("username")String username);

    /**
     * 查询所有
     * @return
     */
    List<RoleInfo> getAll();


    /**
     * 用户和角色的id值
     * @param ids
     */
    void RoleUserInsert(List<RequestVo> ids);

    /**
     * 删除某个用户的所有角色
     * @param userId
     */
    void RoleUserDelete(Integer userId);
}
