package com.nf.library.controller.vo;


import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 书籍信息vo对象
 * 用于验证使用
 * @author Sam
 */
@Data
public class BookInfoVo {
    private Integer id ;
    @NotEmpty
    private String isbn;
    @NotEmpty
    private String bookName;
    @NotEmpty
    private String bookAuthor;
    @NotEmpty
    private String bookType;
    @NotEmpty
    private String bookPublish;
    @Min(1)
    private BigDecimal bookPrice;
    @Min(1)
    private Integer tmamount ;
    @Min(1)
    private Integer bookStock = this.tmamount;
    private Integer bookState;

}
