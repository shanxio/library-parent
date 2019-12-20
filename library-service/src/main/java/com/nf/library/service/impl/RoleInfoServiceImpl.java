package com.nf.library.service.impl;


import com.nf.library.dao.RoleInfoDao;
import com.nf.library.entity.ReaderInfo;
import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.entity.UserInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<RoleInfo> getSearchAll(RoleInfo role, int pageNum, int pageSize) {
        return roleInfoDao.getSearchAll(role,pageNum,pageSize);
    }


    @Override
    public void roleUserInsert(List<RequestVo> ids) {
        roleInfoDao.roleUserInsert(ids);
    }

    @Override
    public void roleInfoInsert(RoleInfo roleInfo) {
        roleInfoDao.roleInfoInsert(roleInfo);
    }

    @Override
    public void roleUserDelete(Integer userId) {
        roleInfoDao.roleUserDelete(userId);
    }

    @Transactional(readOnly = false)
    @Override
    public ResponseVo roleDelete(Integer roleId) {
        List<UserInfo> userInfo = roleInfoDao.getRoleIdUser(roleId);
        ResponseVo responseVo = null;
        if(userInfo.size()!=0){
            responseVo = ResponseVo.builder().code("500").msg("该角色正在使用，无法删除").build();
        }else{

            //先删除角色的权限
            roleInfoDao.roleNodeDelete(roleId);
            //在删除角色
            roleInfoDao.roleDelete(roleId);
            responseVo = ResponseVo.builder().code("200").msg("删除成功").build();
        }
        return responseVo;
    }

    @Override
    public void roleUpdate(RoleInfo roleInfo) {
        roleInfoDao.roleUpdate(roleInfo);
    }


}
