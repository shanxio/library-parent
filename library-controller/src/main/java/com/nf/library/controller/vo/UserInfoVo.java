package com.nf.library.controller.vo;

import com.nf.library.controller.validator.Phone;
import com.nf.library.entity.RequestVo;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 添加使用的vo类
 * @author Sam
 */
@Data
public class UserInfoVo {
    private Integer  userId;
    /**
     * 用户登录姓名
     */
    @NotEmpty
    private String username;
    /**
     * 用户真实姓名
     */
    @NotEmpty
    private String realName;
    /**
     * 性别
     */
    @NotEmpty
    private String userSex;
    /**
     * 手机号码
     */
    @NotEmpty
    @Phone
    private String userPhone;
    /**
     * 登录密码
     */
    @NotEmpty
    private String password;
    /**
     * 日志日期
     */

    private Date userEntrydate;
    /**
     * 用户地址
     */

    @NotEmpty
    private String userAddress;
    /**
     * 用户详细地址
     */

    @NotEmpty
    private String userFulladdress;
    /**
     * 用户照片
     */

    private String userPhoto;
    /**
     * 是否禁用
     */

    private boolean enabled;


    private String userCard;
    /**
     * 角色id
     */
    private Integer[] ids;

    private String rolesName;
    private String roleId;
    private List<GrantedAuthority> authorities =null;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
}
