package com.nf.library.controller.be.userinfo;

import com.github.pagehelper.PageInfo;
import com.nf.library.controller.be.BaseController;
import com.nf.library.controller.vo.UserInfoVo;
import com.nf.library.controller.vo.UserInfoPageVo;
import com.nf.library.entity.UserInfo;
import com.nf.library.security.process.ResponseVo;
import com.nf.library.service.UserInfoService;
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
 * 用户信息的控制类
 * @author Sam
 */
@RestController
@RequestMapping("/admin/user")
public class UserInfoController extends BaseController {


    @Autowired
    private UserInfoService userInfoService;


    @PostMapping("/getAll")
    public PageInfo<UserInfo> getAll(@RequestBody UserInfoPageVo user){
        this.checkNull(user);
        List<UserInfo> userInfos = userInfoService.
                getAll(user.getUserInfo(),user.getPageVo().getPageNum(),user.getPageVo().getPageSize());
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userInfos,user.getPageVo().getPageSize());
        return pageInfo;
    }

    @PostMapping("/userInfoInsert")
    public ResponseVo userInfoInsert(@RequestBody @Valid UserInfoVo userInfoVo, BindingResult bindingResult){
        this.checkNull(userInfoVo);
        this.validException(bindingResult);
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userInfoVo,userInfo);
        userInfoService.userInfoInsert(userInfo);

        return ResponseVo.builder().code("200").msg("添加成功").build();

    }


    @PostMapping("/userInfoDelete")
    public ResponseVo userInfoDelete(Integer id){
        this.checkNull(id);
        userInfoService.userInfoByIdDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }
    @PostMapping("/userInfoBatchDelete")
    public ResponseVo userInfoBatchDelete(@RequestBody Integer[] id){
        this.checkNull(id);
        userInfoService.userInfoBatchDelete(id);
        return ResponseVo.builder().code("200").msg("删除成功").build();
    }

    /**
     * 修改用户状态 是否禁用
     * @param userInfo
     * @return
     */
    @PostMapping("/userInfoStateUpdate")
    public ResponseVo userInfoStateUpdate(@RequestBody UserInfo userInfo){
        this.checkNull(userInfo);
        userInfoService.userInfoUpdate(userInfo);
        return ResponseVo.builder().code("200").msg("修改成功").build();
    }
}
