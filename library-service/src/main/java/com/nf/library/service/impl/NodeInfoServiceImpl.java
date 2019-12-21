package com.nf.library.service.impl;

import com.nf.library.dao.NodeInfoDao;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Tree;
import com.nf.library.service.NodeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 节点资源实现类
 * @author Sam
 */
@Service
public class NodeInfoServiceImpl implements NodeInfoService {
    @Autowired
    private NodeInfoDao nodeInfoDao;



    @Override
    public List<NodeInfo> getRoleTag(String roleTag) {

        return nodeInfoDao.getRoleTag(roleTag);
    }






    @Override
    public List<NodeInfo> getChild(Integer pid) {

        return nodeInfoDao.getChild(pid);
    }

    @Override
    public List<NodeInfo> getRoleTagMenu(String username, int nodeType) {
        return nodeInfoDao.getRoleTagMenu(username,nodeType);
    }

    @Override
    public List<Tree> getAll() {

        return getList(nodeInfoDao.getRoleTagMenu(null,0));
    }

    @Override
    public List<Tree> getRoles(String roleTag) {
        List<NodeInfo> nodeInfos = nodeInfoDao.getRoleTag(roleTag);
        return getList(nodeInfos);
    }


    @Override
    public NodeInfo getById(String nodeId) {
        return nodeInfoDao.getById(nodeId);
    }


    public List<Tree> getList(List<NodeInfo> nodeInfos){
        List<Tree> trees = new ArrayList<>();
        for (NodeInfo nodeInfo : nodeInfos) {
            trees.add(Tree.builder().id(nodeInfo.getNodeId()).label(nodeInfo.getNodeDescription()).pid(nodeInfo.getPid()).build());
        }
        //找到所有的1级菜单
        List<Tree> menuList = new ArrayList<>();
        for (Tree tree : trees) {
            if(tree.getPid()!=null) {
                if (tree.getPid() == 0) {
                    menuList.add(tree);
                }
            }
        }

        //为一级菜单设置子菜单准备递归
        for (Tree menu:menuList) {
            //获取父菜单下所有子菜单调用getChildList
            List<Tree> childList = getChildList(menu.getId(),trees);
            menu.setChildren(childList);
        }
        return menuList;
    }
    public List<Tree> getChildList(Integer id, List<Tree> menuList){
        //构建子菜单
        List<Tree> childList = new ArrayList<>();
        //遍历传入的list
        for (Tree menu:menuList) {
            if(menu.getPid()!=null) {
                //所有菜单的父id与传入的根节点id比较，若相等则为该级菜单的子菜单
                if (menu.getPid().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        //递归
        for (Tree m:childList) {
            m.setChildren(getChildList(m.getId(),menuList));
        }
        if (childList.size() == 0){
            return null;
        }
        return childList;
    }

}
