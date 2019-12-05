package com.nf.library.entity;

import lombok.Data;

/**
 * 节点资源实体类
 * @author Sam
 */
@Data
public class NodeInfo {

    private Integer nodeId;
    private String nodeUrl;
    private String nodeDescription;
    private Integer pid;
    private boolean parent;


}
