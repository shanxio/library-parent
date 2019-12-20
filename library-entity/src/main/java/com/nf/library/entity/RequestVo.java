package com.nf.library.entity;


import lombok.Builder;
import lombok.Data;

/**
 * @author Sam
 */
@Data
@Builder
public class RequestVo {
    Integer userId;
    Integer roleId;
    Integer nodeId;
}
