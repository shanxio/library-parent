package com.nf.library.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Tree {
    private Integer id;
    private String label;
    private Integer pid;
    private List<Tree> children;

}
