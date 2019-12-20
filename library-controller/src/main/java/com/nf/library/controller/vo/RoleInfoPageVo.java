package com.nf.library.controller.vo;


import com.nf.library.entity.RoleInfo;
import lombok.Data;

@Data
public class RoleInfoPageVo {
    private PageVo pageVo;
    private String roleName;
}
