package com.nf.library.service.impl;

import com.nf.library.entity.UserInfo;
import com.nf.library.service.RbacService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限判断实现
 * @author Sam
 */
@Service("rbacService")
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if(principal instanceof UserInfo){
            //如果角色名称为admin直接通过
            if("admin".equals(((UserInfo)principal).getUsername().trim())){
                hasPermission = true;
            }else {
                // 保存用户所拥有权限的所有URL
                Set<String> urls = new HashSet<>();
                //读取用户的所有角色
                Collection<GrantedAuthority> authorityList = (Collection<GrantedAuthority>) ((UserInfo) principal).getAuthorities();
                //读取用户的所有uri地址
                for (GrantedAuthority grantedAuthority : authorityList) {

                }
                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }

        return hasPermission;
    }

}
