package com.nf.library.controller.be.bookInfo;

import com.nf.library.controller.be.BaseController;
import com.nf.library.utils.JsonUtils;
import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;

/**
 * 管理图书信息的父类
 * @author Sam
 */
public class BaseBookInfoController extends BaseController {

    /**
     * 验证是否错误
     * @param bindingResult
     */
    public void validException(BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String fieldErrors = JsonUtils.getJsonString(bindingResult.getFieldErrors());
            throw new ValidationException(fieldErrors);
        }
    }
}
