package com.nf.library.entity;

import lombok.Data;

import java.util.List;

/**
 * 节点资源实体类
 * @author Sam
 */
@Data
public class NodeInfo {
    /**
     * 节点id
     */
    private Integer nodeId;
    /**
     * 节点地址，也就是可访问地址
     */
    private String nodeUrl;
    /**
     * 路由地址，前端使用
     */
    private String nodePath;
    /**
     * 节点描述
     */
    private String nodeDescription;
    /**
     * 父节点谁
     */
    private Integer pid;
    /**
     * 按钮表示符
     */
    private String nodeTag;


    /**
     * 菜单唯一标识符
     */
    private String nodeIndex;
    private List<NodeInfo> child;
}
