package com.nf.library.entity;

//import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 日志实体类
 * @author Sam
 */
@Data
@Builder
public class Log {
    private Integer id;
    private String logcontent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operatetime;
    private String operatetype;
    private Integer userid;
    private String realName;
    private Object
            args;
    private Long totalTime;
}
