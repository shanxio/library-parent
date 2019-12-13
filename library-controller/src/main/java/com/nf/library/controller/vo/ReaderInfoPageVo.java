package com.nf.library.controller.vo;

import com.nf.library.entity.ReaderInfo;
import lombok.Data;

/**
 * 图书信息
 * @author Sam
 */
@Data
public class ReaderInfoPageVo {
    private ReaderInfo reader;
    private PageVo pageVo;
}
