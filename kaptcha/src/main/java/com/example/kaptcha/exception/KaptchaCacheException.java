package com.example.kaptcha.exception;

/**验证码缓存相关异常
 * @author novel
 * @date 2019/12/2
 */
public class KaptchaCacheException extends KaptchaException {
    public KaptchaCacheException() {
    }

    public KaptchaCacheException(Throwable e) {
        super(e);
    }

    public KaptchaCacheException(String message) {
        super(message);
    }
}
