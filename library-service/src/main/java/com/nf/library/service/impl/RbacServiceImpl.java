package com.nf.library.service.impl;

import com.nf.library.dao.NodeInfoDao;
import com.nf.library.entity.NodeInfo;
import com.nf.library.entity.UserInfo;
import com.nf.library.service.RbacService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 权限判断实现
 * @author Sam
 */
@Service("rbacService")
@Slf4j
public class RbacServiceImpl implements RbacService {
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private NodeInfoDao nodeInfoDao;

    private Map<String,List<NodeInfo>> nodeInfoMap = new HashMap<>();
    private static final String userName= "admin";
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        HttpSession session = request.getSession();

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if(principal instanceof UserInfo){
            //如果角色名称为admin直接通过
            if(userName.equals(((UserInfo)principal).getUsername().trim())){
                hasPermission = true;
            }else {
                //初始化所有的可用url
                Set<String> urls = init((UserInfo) principal);

                log.info(((UserInfo) principal).getUsername()+"的所有可用uris："+urls.toString());
                for (String url : urls) {
                    if (antPathMatcher.match(url, request.getRequestURI())) {
                        hasPermission = true;
                        break;
                    }
                }
            }
        }

        return hasPermission;
    }

    private Set<String> init(UserInfo principal) {
        // 保存用户所拥有权限的所有URL
        Set<String> urls = new HashSet<>();
        //读取用户的所有角色
        Collection<GrantedAuthority> authorityList = (Collection<GrantedAuthority>) principal.getAuthorities();

//        List<NodeInfo> nodeInfos = new ArrayList<>(10);
        //读取用户的所有uri地址
        for (GrantedAuthority grantedAuthority : authorityList) {
            //保存用户所用的节点信息
            List<NodeInfo> nodeInfos = nodeInfoDao.getRoleTag(grantedAuthority.getAuthority());
            for (NodeInfo nodeInfo : nodeInfos) {
                String[] nodeUrls = nodeInfo.getNodeUrl().split(",");
                log.info(nodeUrls.toString());
                for (String nodeUrl : nodeUrls) {
                    urls.add(nodeUrl);
                }

            }
        }
//        log.info("用户所有的节点信息："+nodeInfos);
//        if(nodeInfos!=null){
//            //清除
//            session.removeAttribute("nodeInfos");
//        }
//        //保存至会话中，以便可以随时使用
//        session.setAttribute("nodeInfos",nodeInfos);
        return urls;
    }



}
