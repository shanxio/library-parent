package com.nf.library.service;

import com.nf.library.dao.NodeInfoDao;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Tree;
import com.nf.library.service.config.ServiceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfig.class)
public class NodeInfoDaoTest {


    @Autowired
    private NodeInfoDao nodeInfoDao;
    @Test
    public void ztree(){
        //层级获取菜单
         List<NodeInfo> nodeInfos = nodeInfoDao.getAll();
         List<Tree> trees = new ArrayList<>();
        for (NodeInfo nodeInfo : nodeInfos) {
            trees.add(Tree.builder().id(nodeInfo.getNodeId()).label(nodeInfo.getNodeDescription()).pid(nodeInfo.getPid()).build());
        }
        //找到所有的1级菜单
        List<Tree> menuList = new ArrayList<>();
        for (Tree tree : trees) {
            if (tree.getPid()==0){
                menuList.add(tree);
            }
        }

        //为一级菜单设置子菜单准备递归
        for (Tree menu:menuList) {
            //获取父菜单下所有子菜单调用getChildList
            List<Tree> childList = getChildList(menu.getId(),trees);
            menu.setChildren(childList);
        }

        System.out.println("");
    }

    public List<Tree> getChildList(Integer id,List<Tree> menuList){
        //构建子菜单
        List<Tree> childList = new ArrayList<>();
        //遍历传入的list
        for (Tree menu:menuList) {
            //所有菜单的父id与传入的根节点id比较，若相等则为该级菜单的子菜单
            if (menu.getPid().equals(id)){
                childList.add(menu);
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
