package com.nf.library.controller.validator;

import org.hibernate.validator.cfg.ConstraintDef;
import org.hibernate.validator.cfg.context.Constrainable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 身份证号码验证
 * @author Sam
 */
public class CardValidator implements ConstraintValidator<Card,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String card = "/^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$/";
        Pattern pattern = Pattern.compile(card);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
