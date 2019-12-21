package com.nf.library.controller.be.nodeInfo;

import com.nf.library.controller.be.BaseController;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.Tree;
import com.nf.library.service.NodeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 此控制器是用来生成授权的树形节构的
 * @author Sam
 */
@RestController
@RequestMapping("/admin/nodeInfo")
@Slf4j
public class NodeInfoGenerateZthreeController extends BaseController {
    @Autowired
    private NodeInfoService nodeInfoService;



    @PostMapping("tree")
    public List<Tree> zTree(){
       return nodeInfoService.getAll();
    }


    @PostMapping("roleTree")
    public List<Tree> roleTree(String roleTag){
        return nodeInfoService.getRoles(roleTag);
    }

}
