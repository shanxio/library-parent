package com.nf.library.security.filter;

import org.springframework.security.core.AuthenticationException;

public class VerficationAuthenticationException extends AuthenticationException {
    public VerficationAuthenticationException(String msg) {

        super("验证码错误");


    }
}
