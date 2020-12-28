package com.cyw.info_admin.exception;

/**
 * 自定义登录异常
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
