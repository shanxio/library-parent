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
    List<NodeInfo> getRoleTag( String roleTag);


    /**
     * 根据角色标识符，和 权限类型进行查询
     * @param roleTag
     * @return
     */
    List<NodeInfo> getRoleTagMenu(String roleTag, int nodeType);

    /**
     * 生成所有的树菜单
     * @return
     */
    List<Tree> getMenusAll();

    /**
     * 根据对应的角色生成树菜单
     * @param roleTag
     * @return
     */
    List<Tree> getMenusRoles(String roleTag);
    /**
     * 根据角色生成对应的按钮权限
     * @param roleTag
     * @return
     */
    List<Tree> getBtnRoles(String roleTag);

    /**
     * 生成所有的按钮权限
     * @return
     */
    List<Tree> getBtns();



    /**
     * 根据id来找对应的权限
     * @param nodeId
     * @return
     */
    NodeInfo getById(String nodeId);
}
