package com.nf.library.controller.vo;


import com.nf.library.utils.ExcelConfig;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 书籍信息vo对象
 * 用于验证使用
 * @author Sam
 */
@Data
public class BookInfoVo {
    @ExcelConfig(value = "编号")
    private Integer id ;
    @NotEmpty
    @ExcelConfig(value = "isbn")
    private String isbn;
    @NotEmpty
    @ExcelConfig(value = "图书名称")
    private String bookName;
    @NotEmpty
    @ExcelConfig(value = "作者")
    private String bookAuthor;
    @NotEmpty
    @ExcelConfig(value = "类型")
    private String bookType;
    @ExcelConfig(value = "出版日期")
    private Date bookPublish;
    @Min(1)
    @ExcelConfig(value = "价格")
    private BigDecimal bookPrice;
    @Min(1)
    @ExcelConfig(value = "库存册数")
    private Integer tmamount ;
    @Min(1)
    @ExcelConfig(value = "现存册数")
    private Integer bookStock = this.tmamount;
    private Integer bookState;

    private String typeName;
    private String bookHouse;

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
