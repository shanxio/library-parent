package com.nf.library.service.impl;

import com.nf.library.dao.RoleDao;
import com.nf.library.dao.UserDao;
import com.nf.library.entity.Role;
import com.nf.library.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * 自定义登录用户
 * @author Sam
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;
    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        //找到对应的用户
        User user = userDao.getByloginName(loginName);

        if(user!=null){
            //查询该用户所有的角色
            List<Role> roles = roleDao.getRoleByloginName(loginName);
            //存放所有的权限
            List<GrantedAuthority> authorities = new ArrayList<>(5);
            for (Role role : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleTag());
                authorities.add(grantedAuthority);
            }
            user.setAuthorities(authorities);
        }
        return user;
    }
}
