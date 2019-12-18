package com.nf.library.controller.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * 添加使用的vo类
 * @author Sam
 */
@Data
public class UserInfoVo {
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
     * 部门id
     */

    private Integer deptId;
    /**
     * 手机号码
     */
    @NotEmpty
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

    @NotEmpty
    private String userPhoto;
    /**
     * 是否禁用
     */

    private boolean enabled;



}
