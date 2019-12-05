package com.nf.library.entity;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ztree {
    private Integer id;
    private String name;
    private boolean isParent;
    private boolean open;
    private Integer pid;
    private boolean checked = false;
    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }
}
