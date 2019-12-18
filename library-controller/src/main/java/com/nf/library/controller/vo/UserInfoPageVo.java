package com.nf.library.controller.vo;

import com.nf.library.entity.UserInfo;
import lombok.Data;

/**
 * userinfo查询vo类
 * @author Sam
 */
@Data
public class UserInfoPageVo {
    private PageVo pageVo;
    private UserInfo userInfo;
}
