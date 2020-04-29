package com.example.kaptcha.exception;

/**
 * @author novel
 * @date 2019/9/28
 */
public class KaptchaNotFoundException extends KaptchaException {
    public KaptchaNotFoundException() {
        super("Kaptcha Not Found");
    }
}
