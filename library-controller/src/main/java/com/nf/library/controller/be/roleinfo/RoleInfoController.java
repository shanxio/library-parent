package com.nf.library.controller.be.roleinfo;


import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.RoleInfoPageVo;
import com.nf.library.controller.vo.RoleInfoVo;
import com.nf.library.entity.RoleInfo;
import com.nf.library.execption.vo.ResponseVo;
import com.nf.library.service.RoleInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 关于角色的控制类
 * @author Sam
 */
@RestController
@RequestMapping("/admin/role")
public class RoleInfoController extends BaseController {
    @Autowired
    private RoleInfoService roleInfoService;
    @RequestMapping("/getRoles")
    public List<RoleInfo> getRoles(){

        return roleInfoService.getAll();
    }

    @RequestMapping("/getAll")
    public PageInfo<RoleInfo> getRole(@RequestBody RoleInfoPageVo roleInfoPageVo){
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleName(roleInfoPageVo.getRoleName());
        List<RoleInfo> roleInfos =  roleInfoService.getSearchAll(
                roleInfo,
                roleInfoPageVo.getPageVo().getPageNum(),
                roleInfoPageVo.getPageVo().getPageSize());

        PageInfo<RoleInfo> pageInfo = new PageInfo<>(roleInfos);
        return pageInfo;
    }
    @PostMapping("/getUserRoles")
    public List<RoleInfo> getUserRoles(String username){

        return roleInfoService.getRoleByUsername(username);
    }


    @PostMapping("/roleInfoInsert")
    public ResponseVo roleInfoInsert(@RequestBody @Valid RoleInfoVo roleInfoVo, BindingResult bindingResult){
        this.checkNull(roleInfoVo);
        this.validException(bindingResult);
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(roleInfoVo,roleInfo);
        roleInfoService.roleInfoInsert(roleInfo);
        return ResponseVo.builder().code("200").msg("添加成功").build();
    }


    @PostMapping("/roleInfoDelete")
    public ResponseVo roleInfoDelete(Integer roleId){
        this.checkNull(roleId);
        return roleInfoService.roleDelete(roleId);
    }
    @PostMapping("/roleInfoUpdate")
    public ResponseVo roleInfoUpdate(@RequestBody
                                         @Valid RoleInfoVo roleInfoVo,
                                     BindingResult bindingResult){
        this.checkNull(roleInfoVo);
        this.validException(bindingResult);
        RoleInfo roleInfo = new RoleInfo();
        BeanUtils.copyProperties(roleInfoVo,roleInfo);
        roleInfoService.roleUpdate(roleInfo);
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }
}
