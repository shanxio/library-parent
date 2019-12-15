package com.nf.library.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 读者信息表
 * @author Sam
 */
@Data
public class
ReaderInfo {
    private Integer id;
    private String readerName;
    private String readerPhone;
    private String readerAddress;
    private String readerFullAddress;
    private String readerCard;
    /**
     * 读者状态 0 为正常 ，1 为禁用
     */
    private String readerState;
    /**
     * 读者借书金额
     */
    private BigDecimal readerMoney;
    private Date createDate;
}
