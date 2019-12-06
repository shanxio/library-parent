package com.nf.library.utils;


import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExportExcelUtilsTest {

    @Test
    public void ExportUtilsTest() throws FileNotFoundException {
        ExportExcelUtil<testData> util = new ExportExcelUtil<testData>();
        // 准备数据
        List<testData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
          list.add(new testData(1,"wsx","男"));
          list.add(new testData(2,"zjx","女"));
        }
        util.exportExcel2003("用户表", testData.class,
                list, new FileOutputStream("E:/"+ UUID.randomUUID() +".xls"), ExportExcelUtil.EXCEL_FILE_2003);
    }
}
