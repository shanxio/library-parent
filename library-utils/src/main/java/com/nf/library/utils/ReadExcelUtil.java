package com.nf.library.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * 读取excel表格
 * @author Sam
 */
public class ReadExcelUtil {
//        /**
//         * 读取2003Excel
//         * @param filepath 文件路径
//         * @param filename 文件名，包括扩展名
//         * @param startrow 开始行号，索引从0开始
//         * @param startcol 开始列号，索引从0开始
//         * @param sheetnum 工作簿，索引从0开始
//         * @return
//         */
//        public List<Map<String,String>> readExcel2003(String filepath, String filename, int startrow, int startcol, int sheetnum) {
//            List<Map<String,String>> varList = new ArrayList<>();
//
//            try {
//                File target = new File(filepath, filename);
//                FileInputStream fis = new FileInputStream(target);
//                HSSFWorkbook wb = new HSSFWorkbook(fis);
//                fis.close();
//                HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
//                int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号
//
//                HSSFRow rowTitle = sheet.getRow(0);
//                int cellTitleNum = rowTitle.getLastCellNum(); 			//标题行的最后一个单元格位置
//                String[] title = new String[cellTitleNum];
//                for(int i = startcol; i < cellTitleNum; i++){
//                    HSSFCell cell=rowTitle.getCell(Short.parseShort(i+""));
//                    if (cell != null) {
//                        cell.setCellType(Cell.CELL_TYPE_STRING);
//                        title[i] = cell.getStringCellValue();
//                    } else {
//                        title[i] = "";
//                    }
//                }
//
//                for (int i = startrow+1; i < rowNum; i++) {					//行循环开始
//                    Map<String,String> varpd = new HashMap<>();
//                    HSSFRow row = sheet.getRow(i); 							//行
//
//                    for (int j = startcol; j < cellTitleNum; j++) {				//列循环开始
//
//                        HSSFCell cell = row.getCell(Short.parseShort(j + ""));
//                        String cellValue = "";
//                        if (cell != null) {
//                            cell.setCellType(Cell.CELL_TYPE_STRING);//把类型先设置为字符串类型
//                            cellValue = cell.getStringCellValue();
//                        }
//                        varpd.put(title[j], cellValue);
//                    }
//                    varList.add(varpd);
//                }
//                wb.close();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//            return varList;
//        }
    /**
     * 读取2003Excel
     * @param startrow 开始行号，索引从0开始
     * @param startcol 开始列号，索引从0开始
     * @param sheetnum 工作簿，索引从0开始
     * @return
     */
    public List<Map<String,String>> readExcel2003( FileInputStream fis,int startrow, int startcol, int sheetnum) {
        List<Map<String,String>> varList = new ArrayList<>();

        try {
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            fis.close();
            HSSFSheet sheet = wb.getSheetAt(sheetnum); 					//sheet 从0开始
            int rowNum = sheet.getLastRowNum() + 1; 					//取得最后一行的行号

            HSSFRow rowTitle = sheet.getRow(0);
            int cellTitleNum = rowTitle.getLastCellNum(); 			//标题行的最后一个单元格位置
            String[] title = new String[cellTitleNum];
            for(int i = startcol; i < cellTitleNum; i++){
                HSSFCell cell=rowTitle.getCell(Short.parseShort(i+""));
                if (cell != null) {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    title[i] = cell.getStringCellValue();
                } else {
                    title[i] = "";
                }
            }

            for (int i = startrow+1; i < rowNum; i++) {					//行循环开始
                Map<String,String> varpd = new HashMap<>();
                HSSFRow row = sheet.getRow(i); 							//行

                for (int j = startcol; j < cellTitleNum; j++) {				//列循环开始

                    HSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    String cellValue = "";
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);//把类型先设置为字符串类型
                        cellValue = cell.getStringCellValue();
                    }
                    varpd.put(title[j], cellValue);
                }
                varList.add(varpd);
            }
            wb.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return varList;
    }

    //根据类对象返回对应的包装类型的值
    public  Object setParamType(Class clz,String value) throws Exception {
        Object obj = null;
        if(clz==Integer.class){
            obj=Integer.parseInt(value);
        }else if(clz==String.class){
            obj=value;
        }else if(clz==Boolean.class){
            obj=Boolean.parseBoolean(value);
        }else if(clz==Double.class){
            obj=Double.parseDouble(value);
        }else if(clz==Float.class){
            obj=Float.parseFloat(value);
        }else if(clz==Byte.class){
            obj=Byte.parseByte(value);
        }else if(clz==Long.class){
            obj=Long.parseLong(value);
        }else if(clz==Short.class){
            obj=Short.parseShort(value);
        }else if(clz== BigDecimal.class){

            obj = new BigDecimal(value);
        }else if(clz == Date.class){
            obj = HSSFDateUtil.getJavaDate(Double.parseDouble(value));

        }
        return obj;
    }

}
