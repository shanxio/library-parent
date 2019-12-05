package com.nf.library.entity;


import lombok.Data;

import java.math.BigDecimal;

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
    private String bookPublish;
    private BigDecimal bookPrice;
    private Integer tmamount;
    private Integer bookStock;
    private Integer bookState;

}
