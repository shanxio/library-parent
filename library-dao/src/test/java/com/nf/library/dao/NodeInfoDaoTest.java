package com.nf.library.dao;

import com.nf.library.dao.config.DaoConfig;
import com.nf.library.entity.NodeInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoConfig.class)
public class NodeInfoDaoTest {
    @Autowired
    private NodeInfoDao nodeInfoDao;
    @Test
    public void getRoleTag() {
        List<NodeInfo> nodeInfos = nodeInfoDao.getRoleTag("ROLE_DENG");
        System.out.println(nodeInfos);
    }
}
