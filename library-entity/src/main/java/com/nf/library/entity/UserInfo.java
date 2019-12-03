package com.nf.library.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 用户信息实体
 * @author Sam
 */
@Data
public class UserInfo implements UserDetails {
    private Integer userId;
    private String username;
    private String realName;
    private String userSex;
    private Integer deptId;
    private String deptName;
    private String userPhone;
    private String password;
    private Date userEntrydate;
    private boolean enabled;

    /**
     * 该用户的权限集合
     */
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(10);
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return this.authorities;
    }

    /**
     * 设置权限集合
     * @param authorities
     */
    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return  true;
    }

    @Override
    public boolean isEnabled() {

        return this.enabled;
    }
}
