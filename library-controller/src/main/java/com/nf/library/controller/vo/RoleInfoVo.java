package com.nf.library.controller.vo;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Sam
 */
@Data
public class RoleInfoVo {
    private Integer  roleId;

    @NotEmpty
    private String roleName;
    @NotEmpty
    private String roleTag;
    @NotEmpty
    private String roleDescription;
}
