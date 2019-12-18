package com.nf.library.service;

import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import org.apache.ibatis.annotations.Param;

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
