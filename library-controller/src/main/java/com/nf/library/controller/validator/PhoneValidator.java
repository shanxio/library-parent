package com.nf.library.controller.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * 手机号码的具体验证逻辑
 * @author Sam
 */
public class PhoneValidator
        implements ConstraintValidator<Phone,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String phoneValid = "^1[3|4|5|7|8][0-9]{9}$";
        Pattern pattern = Pattern.compile(phoneValid);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }


}
