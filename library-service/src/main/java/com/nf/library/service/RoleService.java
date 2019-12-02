package com.nf.library.service;

import com.nf.library.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色的业务接口
 * @author Sam
 */
public interface RoleService {
    /**
     * 根据登录用户查找对应的角色
     * @param loginName
     * @return
     */
    List<Role> getRoleByloginName(String loginName);
}
