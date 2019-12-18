package com.nf.library.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


/**
 * 用户信息实体
 * @author Sam
 */
@Data
public class UserInfo implements UserDetails {
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 用户登录姓名
     */
    private String username;
    /**
     * 用户真实姓名
     */
    private String realName;
    /**
     * 性别
     */
    private String userSex;
    /**
     * 部门id
     */
    private Integer deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 手机号码
     */
    private String userPhone;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 日志日期
     */
    private Date userEntrydate;
    /**
     * 用户地址
     */
    private String userAddress;
    /**
     * 用户详细地址
     */
    private String userFulladdress;
    /**
     * 用户照片
     */
    private String userPhoto;
    /**
     * 是否禁用
     */
    private boolean enabled;




    private String rolesName;
    /**
     * 该用户的角色标识符集合
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
