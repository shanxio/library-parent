package com.nf.library.service.impl;

import com.nf.library.dao.RoleInfoDao;
import com.nf.library.dao.UserInfoDao;
import com.nf.library.entity.RoleInfo;
import com.nf.library.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 自定义登录用户
 * @author Sam
 */
@Service("userDetailsServiceImpl")
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RoleInfoDao roleInfoDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //找到对应的用户
        UserInfo userInfo = userInfoDao.getByUsername(username);

        if(userInfo !=null){
            //查询该用户所有的角色
            List<RoleInfo> roleInfos = roleInfoDao.getRoleByUsername(username);
            //存放所有的角色
            List<GrantedAuthority> authorities = new ArrayList<>(5);
            String rolesName = "";
            for (RoleInfo roleInfo : roleInfos) {
                //读取角色
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleInfo.getRoleTag());
                authorities.add(grantedAuthority);
                rolesName+=roleInfo.getRoleName()+"|";
            }

            userInfo.setRolesName(rolesName.substring(0,rolesName.lastIndexOf("|")));
            log.info("登录用户名称为："+username+"----用户角色分别有："+authorities);
            userInfo.setAuthorities(authorities);
        }

        return userInfo;
    }
}
