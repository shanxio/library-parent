package com.nf.library.security.process;


import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.UserInfo;
import com.nf.library.service.NodeInfoService;
import com.nf.library.utils.JsonUtils;
import com.nf.library.utils.JwtSubject;
import com.nf.library.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 自定义成功处理
 * @author Sam
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    private NodeInfoService nodeInfoService;

    public MyAuthenticationSuccessHandler(NodeInfoService nodeInfoService) {
        this.nodeInfoService = nodeInfoService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
//        // 允许跨域
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        // 允许自定义请求头token(允许head跨域)
//        response.setHeader("Access-Control-Allow-Headers",
//                "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");

        response.setContentType("application/json;charset=utf-8");
        //生成token信息
        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        JwtSubject jwtSubject = JwtSubject.builder().username(userInfo.getUsername()).build();
        String jwtToken =
                JwtUtils.createJWT(
                        UUID.randomUUID().toString(),
                        "wsx",
                        JwtUtils.generalSubject(jwtSubject),1*60*10000);



        ResponseVo responseVo = null;
            //获取用户信息
            responseVo = ResponseVo.builder()
                    .code("200")
                    .name(userInfo.getUsername())
                    .data(userInfo.getRolesName())
                    .token(jwtToken).build();
        setRouterAndMenus(userInfo,responseVo);
        setBtns(userInfo,responseVo);
        JsonUtils.write(response.getOutputStream(),responseVo);
    }


    private void setBtns(UserInfo userInfo,ResponseVo responseVo){
        //读取用户的所有角色
        List<GrantedAuthority> authorityList =  (List<GrantedAuthority>) userInfo.getAuthorities();
        //保存按钮信息
        Set<NodeInfo> btns = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorityList) {
            List<NodeInfo> nodeInfos = nodeInfoService.getRoleTagMenu(grantedAuthority.getAuthority(),1);
            for (NodeInfo nodeInfo : nodeInfos) {
               btns.add(nodeInfo);
            }
        }
        responseVo.setBtns(btns);
    }
    private void setRouterAndMenus(UserInfo userInfo,ResponseVo responseVo){
        //读取用户的所有角色
        List<GrantedAuthority> authorityList =  (List<GrantedAuthority>) userInfo.getAuthorities();
        Set<NodeInfo> reouts = new HashSet<>();
        Set<NodeInfo> menus = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorityList) {
            List<NodeInfo> nodeInfos = nodeInfoService.getRoleTagMenu(grantedAuthority.getAuthority(),0);
            for (NodeInfo nodeInfo : nodeInfos) {
                if(nodeInfo.getPid()==0) {
                        NodeInfo nodeInfo1 = setChild(nodeInfo, nodeInfo.getNodeId());
                        menus.add(nodeInfo1);
                }
                reouts.add(nodeInfo);
            }
        }
        //添加菜单信息
        responseVo.setMenus(menus);
        //添加路由信息
        responseVo.setRouters(reouts);
    }
    public NodeInfo setChild(NodeInfo nodeInfo,Integer pid){
        List<NodeInfo> nodeInfoList = nodeInfoService.getChild(pid);
        for (NodeInfo info : nodeInfoList) {
            setChild(info, info.getNodeId());
        }
        nodeInfo.setChild(nodeInfoList);
        return nodeInfo;
    }
}
