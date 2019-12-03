package com.nf.library.entity;

import lombok.Builder;
import lombok.CustomLog;
import lombok.Data;

/**
 * 角色实体类
 * @author Sam
 */
@Data
@Builder
public class RoleInfo {
    private Integer  roleId;
    private String roleName;
    private String roleTag;
    private String roleDescription;

}
