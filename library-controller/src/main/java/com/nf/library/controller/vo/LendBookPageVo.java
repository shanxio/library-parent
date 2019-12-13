package com.nf.library.controller.vo;

import com.nf.library.entity.LendBook;
import lombok.Data;

/**
 * @author Sam
 */
@Data
public class LendBookPageVo {
    private LendBook lend;
    private PageVo pageVo;
}
