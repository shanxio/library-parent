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

        response.setContentType("application/json;charset=utf-8");

        UserInfo userInfo = (UserInfo) authentication.getPrincipal();
        //生成token信息
        String jwtToken = generateJwt(userInfo);
        //所有的关于页面的权限
        Set<NodeInfo> getRolesNodeInfs = getRolesNodeInfos(userInfo,0);
        //生成路由信息
        Set<NodeInfo> routers =generateRouters(getRolesNodeInfs);
        //生成菜信息
        Set<NodeInfo> menus =generateMenus(getRolesNodeInfs);
        //所有关于按钮有关的权限
        Set<NodeInfo> getBtnNodeInfs = getRolesNodeInfos(userInfo,1);
        //生成按钮
        Set<NodeInfo> btns = generateBtns(getBtnNodeInfs);

            //获取用户信息
        SuccessVo successVo = SuccessVo.builder()
                            .code("200")
                            .name(userInfo.getUsername())
                            .data(userInfo.getRolesName())
                            //token
                            .token(jwtToken)
                            //路由
                            .routers(routers)
                            //菜单
                            .menus(menus)
                            //按钮
                            .btns(btns).build();
        JsonUtils.write(response.getOutputStream(),successVo);
    }

    /**
     * 生成token令牌
     * @param userInfo
     * @return
     */
    public String generateJwt(UserInfo userInfo){
        JwtSubject jwtSubject = JwtSubject.builder().username(userInfo.getUsername()).build();
        String jwtToken =
                JwtUtils.createJWT(
                        UUID.randomUUID().toString(),
                        "wsx",
                        JwtUtils.generalSubject(jwtSubject),1*60*10000);
        return jwtToken;
    }

    /**
     * 生成按钮的所有信息
     * @param getBtnNodeInfs
     * @return
     */
    private Set<NodeInfo> generateBtns(Set<NodeInfo> getBtnNodeInfs){
        //保存按钮信息
        Set<NodeInfo> btns = new HashSet<>();
        for (NodeInfo nodeInfo : getBtnNodeInfs) {
           btns.add(nodeInfo);
        }
        return btns;
    }

    /**
     * 生成路由
     * @param rolesNodeInfos
     * @return
     */
    private Set<NodeInfo> generateRouters(Set<NodeInfo> rolesNodeInfos){
        Set<NodeInfo> routers = new HashSet<>();
        for (NodeInfo router : rolesNodeInfos) {
             routers.add(router);
        }
        return routers;
    }


    /**
     * 生成菜单
     * @param rolesNodeInfos
     * @return
     */
    private Set<NodeInfo> generateMenus(Set<NodeInfo> rolesNodeInfos){
        Set<NodeInfo> menus = new HashSet<>();
        for (NodeInfo menu : rolesNodeInfos) {
            if(menu.getPid()!=null) {
                if (menu.getPid() == 0) {
                    menus.add(setChid(menu, rolesNodeInfos));
                }
            }
        }
        return menus;
    }

    /**
     * 用户所有的角色
     * @param userInfo
     */
    private Set<NodeInfo> getRolesNodeInfos(UserInfo userInfo,Integer nodeType){
        //读取用户的所有角色
        List<GrantedAuthority> authorityList =  (List<GrantedAuthority>) userInfo.getAuthorities();
        Set<NodeInfo> RolesNodeInfos = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorityList) {
            List<NodeInfo> nodeInfos = nodeInfoService.getRoleTagMenu(grantedAuthority.getAuthority(),nodeType);
            for (NodeInfo nodeInfo : nodeInfos) {
                //保存所有角色的全系
                RolesNodeInfos.add(nodeInfo);
            }
        }
        return RolesNodeInfos;
    }

    /**
     * 设置子节点
     * @param nodeInfo
     * @param rolesNodeInfos
     * @return
     */
    public NodeInfo setChid(NodeInfo nodeInfo,Set<NodeInfo> rolesNodeInfos){
        List<NodeInfo> nodeInfoList = new ArrayList<>();
        for (NodeInfo info : rolesNodeInfos) {
            if(info.getPid()!=null) {
                if (info.getPid().equals(nodeInfo.getNodeId())) {
                    nodeInfoList.add(info);
                }
            }
        }
        nodeInfo.setChild(nodeInfoList);
        return nodeInfo;
    }
}
