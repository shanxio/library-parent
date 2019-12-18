package com.nf.library.controller.be;

import com.nf.library.utils.ExcelConfig;
import com.nf.library.utils.ReadExcelUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * 此父类主要是用来验证表格使用
 * @author Sam
 */
public class BaseVaildExcelController extends BaseController {

    protected  <T> List<T> setVaild(List<T> objs) {
        List<T> errors = new ArrayList<>();
        List<Integer> errorId = new ArrayList<>();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        for (int i = 0;i<objs.size();i++) {
            //开始校验
            Set<ConstraintViolation<T>> result = validator.validate(objs.get(i));
            if(result.size()>0){
                errorId.add(i);
                errors.add(objs.get(i));
            }
        }
        for (T error : errors) {
            objs.remove(error);
        }
        return errors;
    }
    /**
     * 此方法用于将表格中的值赋给对应的实体对象
     * @param obj
     * @param getKey
     * @param value
     * @throws Exception
     */
    protected void setEntityValue(Object obj, String getKey, String value) throws Exception {
        ReadExcelUtil read = new ReadExcelUtil();
        // 对应实体类的字段
        Field[] clzFiles =obj.getClass().getDeclaredFields();
        for (int j = 0; j < clzFiles.length ; j++) {
            //判断是否有excelConfig自定义注解
            if(clzFiles[j].isAnnotationPresent(ExcelConfig.class)) {
                //获得对应的注解
                ExcelConfig excelConfig = clzFiles[j].getAnnotation(ExcelConfig.class);
                //对应实体类赋值
                if(excelConfig.value().equals(getKey)){
                    clzFiles[j].setAccessible(true);
                    clzFiles[j].set(obj,read.setParamType(clzFiles[j].getType(),value));
                    clzFiles[j].setAccessible(false);
                    break;
                }
            }
        }
    }
}
