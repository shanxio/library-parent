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
public class Role {
    private Integer  id;
    private String roleName;
    private String roleTag;
    private String description;

}
