package com.nf.library.service.impl;


import com.nf.library.dao.RoleInfoDao;
import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色业务实现类
 * @author Sam
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Autowired
    private RoleInfoDao roleInfoDao;

    @Override
    public List<RoleInfo> getRoleByUsername(String username) {
        return roleInfoDao.getRoleByUsername(username);
    }

    @Override
    public List<RoleInfo> getAll() {
        return roleInfoDao.getAll();
    }

    @Override
    public void RoleUserInsert(List<RequestVo> ids) {
        roleInfoDao.RoleUserInsert(ids);
    }

    @Override
    public void RoleUserDelete(Integer userId) {
        roleInfoDao.RoleUserDelete(userId);
    }
}
