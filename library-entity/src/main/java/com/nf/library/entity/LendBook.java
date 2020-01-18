package com.nf.library.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 借阅表
 * @author Sam
 */
@Data
public class LendBook {
    private Integer id;
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String readerId;
    /**
     * 读者姓名
     */
    private String readerName;
    /**
     * 借阅时间
     */
    private Date lendDate;
    /**
     * 续借次数
     */
    private Integer lendTotalcount;
    /**
     * 借阅金额
     */
    private BigDecimal lendMoney;
    /**
     * 借阅天数
     */
    private Integer lendDay;

    private Integer lendState = 0;
}
