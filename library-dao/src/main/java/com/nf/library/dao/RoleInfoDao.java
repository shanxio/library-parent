package com.nf.library.dao;

import com.nf.library.entity.ReaderInfo;
import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.entity.UserInfo;
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
     * 添加角色
     * @param roleInfo
     */
    void roleInfoInsert(RoleInfo roleInfo);
    /**
     * 用户和角色的id值
     * @param ids
     */
    void roleUserInsert(List<RequestVo> ids);
    /**
     * 查询所有的角色
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<RoleInfo> getSearchAll(
            @Param("role") RoleInfo role,
            @Param("pageNum")int pageNum,
            @Param("pageSize")int pageSize);
    /**
     * 删除某个用户的所有角色
     * @param userId
     */
    void roleUserDelete(Integer userId);

    /**
     * 删除某个角色
     * @param roleId
     */
    void roleDelete(Integer roleId);

    /**
     * 根据角色查询用户
     * @param roleId
     * @return
     */
    List<UserInfo>  getRoleIdUser(Integer roleId);

    /**
     * 删除某个角色下的所有权限
     * @param roldId
     */
    void roleNodeDelete(Integer roldId);


    /**
     * 修改角色
     * @param roleInfo
     */
    void roleUpdate(@Param("roleInfo") RoleInfo roleInfo);
}
