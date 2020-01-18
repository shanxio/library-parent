package com.nf.library.controller.be.roleinfo;

import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.AuthVo;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.RequestVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.NodeInfoService;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sam
 */
@RestController
@RequestMapping("/admin/role")
public class RoleInfoAuthorizationController extends BaseController {


    @Autowired
    private RoleInfoService roleInfoService;
    @Autowired
    private NodeInfoService nodeInfoService;
    @PostMapping("/auth")
    public ResponseVo auth(@RequestBody AuthVo authVo){
        this.checkNull(authVo);
        Integer[] nodeInfos = authVo.getNodeInfos();
        String roleTag =authVo.getRoleTag();
        RoleInfo roleInfo = roleInfoService.getRoleByTag(roleTag);
        if(nodeInfos.length!=0) {
            List<RequestVo> requestVos = new ArrayList<>();
            for (Integer nodeInfo : nodeInfos) {
                NodeInfo node = nodeInfoService.getById(nodeInfo.toString());
                String nodeIds = node.getNodeIds();
                if (nodeInfos != null) {
                    String[] nodeId = nodeIds.split(",");
                    for (String id : nodeId) {
                        requestVos.add(RequestVo.builder().roleId(roleInfo.getRoleId()).nodeId(Integer.parseInt(id)).build());
                    }
                }
            }
            roleInfoService.roleNodeInsert(requestVos,roleInfo.getRoleId());
        }else{
            roleInfoService.roleNodeDelete(roleInfo.getRoleId());
        }
        return ResponseVo.builder().code("200").msg("授权成功").build();

    }
}
