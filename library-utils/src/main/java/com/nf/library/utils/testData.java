package com.nf.library.utils;


import lombok.CustomLog;
import lombok.Data;

@Data
public class testData {
    @ExcelConfig(value = "编号")
    private Integer id;
    @ExcelConfig(value = "姓名" )
    private String name;
    @ExcelConfig(value = "性别")
    private String sex;

    public testData(Integer id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }
}
