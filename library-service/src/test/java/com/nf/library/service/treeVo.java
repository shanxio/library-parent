package com.nf.library.service;

import lombok.Data;

import java.util.List;

@Data
public class treeVo {
    private Integer id;
    private String lable;
    private List<treeVo> children;
}
