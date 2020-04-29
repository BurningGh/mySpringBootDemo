package com.example.kaptcha.exception;

/**
 * KaptchaException
 *
 * @author novel
 * @date 2019/9/28
 */
public class KaptchaException extends RuntimeException {

    public KaptchaException() {
        super();
    }

    public KaptchaException(Throwable e) {
        super(e);
    }

    public KaptchaException(String message) {
        super(message);
    }
}
