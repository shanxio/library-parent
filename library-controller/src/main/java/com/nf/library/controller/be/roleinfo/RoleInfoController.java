package com.nf.library.controller.be.roleinfo;


import com.nf.library.entity.RoleInfo;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 关于角色的控制类
 * @author Sam
 */
@RestController
@RequestMapping("/admin/role")
public class RoleInfoController {
    @Autowired
    private RoleInfoService roleInfoService;
    @RequestMapping("/getRoles")
    public List<RoleInfo> getRole(){
        return roleInfoService.getAll();
    }
}
