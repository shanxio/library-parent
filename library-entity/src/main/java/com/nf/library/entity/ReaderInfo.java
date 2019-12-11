package com.nf.library.entity;

import lombok.Data;

/**
 * 读者信息表
 * @author Sam
 */
@Data
public class ReaderInfo {
    private Integer id;
    private String readerName;
    private String readerPhone;
    private String readerAddress;
    /**
     * 读者状态 0 为正常 ，1 为禁用
     */
    private String readerState;
}
