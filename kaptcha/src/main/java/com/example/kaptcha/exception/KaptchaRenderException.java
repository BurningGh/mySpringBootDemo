package com.example.kaptcha.exception;

import java.io.IOException;

/**
 * @author novel
 * @date 2019/9/28
 */
public class KaptchaRenderException extends KaptchaException {

    public KaptchaRenderException(IOException e) {
        super(e);
    }

}
