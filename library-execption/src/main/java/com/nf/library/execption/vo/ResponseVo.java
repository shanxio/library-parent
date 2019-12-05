package com.nf.library.execption.vo;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Sam
 */
@Data
@Builder
public class ResponseVo {
    private String code;
    private String msg;
    private Object date;

}
