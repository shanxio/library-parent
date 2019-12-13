package com.nf.library.controller.be.nodeInfo;

import com.nf.library.controller.be.BaseController;
import com.nf.library.entity.Ztree;
import com.nf.library.service.NodeInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * 此控制器是用来生成授权的树形节构的
 * @author Sam
 */
@Controller
@RequestMapping("/admin/nodeInfo")
@Slf4j
public class NodeInfoGenerateZthreeController extends BaseController {
    @Autowired
    private NodeInfoService nodeInfoService;
    @PostMapping("/ztree")
    @ResponseBody
    public List<Ztree> zTree(HttpSession session,@RequestParam(value = "id",defaultValue = "0") Integer pid){
        //根据ztree传回的值进行异步加载
        List<Ztree> ztrees = nodeInfoService.getByPid(pid,session);
        log.info("树形结构数据："+ztrees);
        return ztrees;
    }
}
