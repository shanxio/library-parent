package com.nf.library.execption.vo;

import lombok.Builder;
import lombok.CustomLog;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
