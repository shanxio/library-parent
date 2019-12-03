package com.nf.library.dao;

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
}
