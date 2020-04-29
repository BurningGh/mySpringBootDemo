package com.example.kaptcha.exception;

/**
 * @author novel
 * @date 2019/9/28
 */
public class KaptchaTimeoutException extends KaptchaException {
    public KaptchaTimeoutException() {
        super("Kaptcha is timeout");
    }
}
