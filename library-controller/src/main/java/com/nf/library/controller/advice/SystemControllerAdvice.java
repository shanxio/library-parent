package com.nf.library.controller.advice;

import com.nf.library.execption.AppException;
import com.nf.library.execption.vo.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * 错误处理
 * @author Sam
 */
@RestControllerAdvice
public class SystemControllerAdvice {
    @ExceptionHandler(AppException.class)
    public ResponseVo handlerV(AppException app){
        ResponseVo responseVo = ResponseVo.builder().code("500").msg(app.getMessage()).build();
        return responseVo;
    }
}
