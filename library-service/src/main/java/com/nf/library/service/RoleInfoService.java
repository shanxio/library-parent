package com.nf.library.service;

import com.nf.library.entity.ReaderInfo;
import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.execption.vo.ResponseVo;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;

/**
 * 角色的业务接口
 * @author Sam
 */
public interface RoleInfoService {
    /**
     * 根据登录用户查找对应的角色
     * @param username
     * @return
     */
    List<RoleInfo> getRoleByUsername(String username);

    /**
     * 查询所有
     * @return
     */
    List<RoleInfo> getAll();

    /**
     * 查询所有的角色
     * @param role
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<RoleInfo> getSearchAll(
            RoleInfo role,
           int pageNum,
            int pageSize);
    /**
     * 用户和角色的id值
     * @param ids
     */
    void roleUserInsert(List<RequestVo> ids);


    /**
     * 用户和权限的id值，用于授权使用
     * @param ids
     */
    void roleNodeInsert(List<RequestVo> ids);

    /**
     * 添加角色
     * @param roleInfo
     */
    void roleInfoInsert(RoleInfo roleInfo);
    /**
     * 删除某个用户的所有角色
     * @param userId
     */
    void roleUserDelete(Integer userId);



    /**
     * 删除某个角色
     * @param roleId
     */
     ResponseVo roleDelete(Integer roleId);


    /**
     * 修改角色
     * @param roleInfo
     */
    void roleUpdate(RoleInfo roleInfo);

    /**
     * 根据标识查找对应的角色
     * @param roleTag
     * @return
     */
    RoleInfo getRoleByTag(String roleTag);
}
