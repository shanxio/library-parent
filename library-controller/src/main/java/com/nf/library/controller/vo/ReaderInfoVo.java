package com.nf.library.controller.vo;

import com.nf.library.controller.validator.Phone;
import com.nf.library.utils.ExcelConfig;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Sam
 */
@Data
public class ReaderInfoVo {
    @NotEmpty(message = "姓名不能为空")
    @ExcelConfig("姓名")

    private String readerName;
    @NotEmpty(message = "手机号码不能为空")
    @Phone
    @ExcelConfig("手机号码")
    private String readerPhone;
    @NotEmpty(message = "地址不能为空")
    @ExcelConfig("地址")
    private String readerAddress;
    @NotEmpty(message = "详细地址不能空")
    @ExcelConfig("详细地址")
    private String readerFullAddress;
    @NotEmpty(message = "身份证号码不能为空")
    @ExcelConfig("身份证号")
    private String readerCard;
    @ExcelConfig("状态")
    private String readerState;
    @Min(value = 10,message = "充值金额不能小于10元")
    @ExcelConfig(value = "充值金额")
    private BigDecimal readerMoney;
    @ExcelConfig(value = "创建日期")

    private Date createDate;

    private String msg;
}
