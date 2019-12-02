package com.nf.library.service.impl;


import com.nf.library.dao.RoleDao;
import com.nf.library.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现类
 * @author Sam
 */
@Service
public class RoleService implements com.nf.library.service.RoleService {
    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> getRoleByloginName(String loginName) {
        return roleDao.getRoleByloginName(loginName);
    }
}
