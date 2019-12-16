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
    private String msg;
    private Object data;
    private Set<NodeInfo> routers;
    private List<NodeInfo> menus;
    private String token;
}
