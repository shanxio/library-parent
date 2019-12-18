package com.nf.library.service.impl;

import com.nf.library.dao.NodeInfoDao;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Ztree;
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
    public List<Ztree> getByPid(Integer pid, HttpSession session) {
        List<Ztree> ztrees = new ArrayList<>();
        List<NodeInfo> nodeInfos = nodeInfoDao.getByPid(pid);
        for (NodeInfo nodeInfo : nodeInfos) {

            Ztree ztree = Ztree.builder()
                    .id(nodeInfo.getNodeId())
                    .name(nodeInfo.getNodeDescription())
//                    .isParent(nodeInfo.isParent())
                    .open(true)
                    .pid(pid).build();
            //获取用户的所拥有的节点信息
            List<NodeInfo> userNodeInfos = (List<NodeInfo>) session.getAttribute("nodeInfos");
            if(userNodeInfos!=null) {
                for (NodeInfo userNodeInfo : userNodeInfos) {
                    //当id相等则选中复选框
                    if (nodeInfo.getNodeId().equals(userNodeInfo.getNodeId())) {
                        ztree.setChecked(true);
                    }
                }
            }else{
                //等于空表示admin用户
                ztree.setChecked(true);
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public List<NodeInfo> getChild(Integer pid) {
        return nodeInfoDao.getChild(pid);
    }

    @Override
    public List<NodeInfo> getRoleTagMenu(String roleTag, int nodeType) {
        return nodeInfoDao.getRoleTagMenu(roleTag,nodeType);
    }


}
