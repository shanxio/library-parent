package com.nf.library.dao;

import com.nf.library.entity.NodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *关于所有权限的相关操作
 * @author Sam
 */
public interface NodeInfoDao {
    /**
     * 根据角色的标识符查找对应的所有资源
     * @return
     */
    List<NodeInfo> getRoleTag(@Param("roleTag") String roleTag);


    /**
     * 根据角色标识符，和 权限类型进行查询
     * @param roleTag
     * @return
     */
    List<NodeInfo> getRoleTagMenu(@Param("roleTag") String roleTag,@Param("nodeType") int nodeType);

    /**
     * 根据pid查询子菜单
     * @param pid
     * @return
     */
    List<NodeInfo> getChild(Integer pid);
    /**
     * 查询子节点
     * @return
     */
    List<NodeInfo> getByPid(Integer pid);
}
