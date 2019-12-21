package com.nf.library.entity;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 书籍信息实体类
 * @author Sam
 */
@Data
public class BookInfo {

    private Integer id ;
    private String isbn;
    private String bookName;
    private String bookAuthor;
    private String bookType;
    private String typeName;
    private String bookHouse;
    /**
     *   出版日期
     */
    private Date bookPublish;
    private BigDecimal bookPrice;
    private Integer tmamount;
    private Integer bookStock;
    private Integer bookState;
    private String bookImage;
    /**
     *  用作查询使用
     *  开始日期
     */
    private Date startDate;
    /**
     * 用作查询使用
     * 结束日期
     */
    private Date endDate;


}
