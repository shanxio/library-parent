package com.nf.library.service;

import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Tree;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 节点业务类
 * @author Sam
 */
public interface NodeInfoService {

    /**
     * 根据角色的标识符查找对应的所有资源
     * @return
     */
    List<NodeInfo> getRoleTag(@Param("roleTag") String roleTag);

    /**
     * 根据pid查询子菜单
     * @param pid
     * @return
     */
    List<NodeInfo> getChild(Integer pid);
    /**
     * 根据角色标识符，和 权限类型进行查询
     * @param roleTag
     * @return
     */
    List<NodeInfo> getRoleTagMenu(String roleTag, int nodeType);


    List<Tree> getAll();

    /**
     * 根据id来找对应的权限
     * @param nodeId
     * @return
     */
    NodeInfo getById(String nodeId);
}
