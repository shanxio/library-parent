package com.nf.library.service;

import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Ztree;
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
     * 查询子节点
     * @return
     */
    List<Ztree> getByPid(Integer pid, HttpSession session);
}
