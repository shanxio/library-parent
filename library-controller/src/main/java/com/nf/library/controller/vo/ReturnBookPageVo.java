package com.nf.library.controller.vo;

import com.nf.library.entity.ReturnBook;
import lombok.Data;

/**
 * @author Sam
 */
@Data
public class ReturnBookPageVo {
    private PageVo pageVo;
    private ReturnBook returnBook;
}
