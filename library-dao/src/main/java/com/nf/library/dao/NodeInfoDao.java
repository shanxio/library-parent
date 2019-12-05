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
     * 查询子节点
     * @return
     */
    List<NodeInfo> getByPid(Integer pid);
}
