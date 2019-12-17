package com.nf.library.security.process;

import com.nf.library.entity.NodeInfo;
import lombok.Builder;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Sam
 */
@Data
@Builder
public class ResponseVo {
    private String code;
    private String name;
    private String msg;
    private Object data;
    /**
     * 路由信息
     */
    private Set<NodeInfo> routers;
    /**
     * 菜单信息
     */
    private List<NodeInfo> menus;
    /**
     * 按钮信息
     */

    private List<NodeInfo> btns;
    /**
     * 登录令牌
     */
    private String token;
}
