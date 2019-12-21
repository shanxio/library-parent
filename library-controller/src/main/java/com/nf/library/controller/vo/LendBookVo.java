package com.nf.library.controller.vo;

import com.nf.library.entity.BookInfo;
import com.nf.library.entity.ReaderInfo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author Sam
 */
@Data
public class LendBookVo {
    private Integer id;
    @NotEmpty
    private String isbn;
    private String bookName;
    private String bookAuthor;
    @NotEmpty
    private String readerId;
    private String readerName;

    private BigDecimal lendMoney;

    @NotEmpty
    private Integer lendDay;

}
