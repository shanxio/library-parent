package com.nf.library.controller.vo;

import com.nf.library.controller.validator.Phone;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @author Sam
 */
@Data
public class ReaderInfoVo {
    @NotEmpty
    private String readerName;
    @NotEmpty
    @Phone
    private String readerPhone;
    @NotEmpty
    private String readerAddress;

    private String readerCard;
    @NotEmpty
    private String readerState;
    @NotEmpty
    @Min(10)
    private BigDecimal readerMoney;
}
