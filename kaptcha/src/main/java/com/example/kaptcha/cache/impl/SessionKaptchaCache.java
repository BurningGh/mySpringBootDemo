package com.example.kaptcha.cache.impl;

import com.example.kaptcha.cache.AbstractKaptchaCache;
import com.example.kaptcha.cache.config.KaptchaCacheProperties;
import com.example.kaptcha.exception.KaptchaNotFoundException;
import com.example.kaptcha.exception.KaptchaTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_DATE;

/**
 * session 存储实现
 *
 * @author novel
 * @date 2019/9/28
 */
@Slf4j
public class SessionKaptchaCache extends AbstractKaptchaCache {

    @Autowired
    private HttpServletRequest request;

    public SessionKaptchaCache(KaptchaCacheProperties kaptchaCacheProperties) {
        super(kaptchaCacheProperties);
    }

    @Override
    public void saveKaptcha(String key, String code) {
        HttpSession session = request.getSession();
        session.setAttribute(getPrefix() + ":" + key, code);
        session.setAttribute(KAPTCHA_SESSION_DATE, System.currentTimeMillis() + getKaptchaCacheProperties().getCacheTimeOut());
    }

    @Override
    public String getKaptcha(String key) {
        HttpSession session = request.getSession();
        Object attribute = session.getAttribute(KAPTCHA_SESSION_DATE);
        if (!ObjectUtils.isEmpty(attribute)) {
            Long timeout = (Long) attribute;
            if (timeout >= System.currentTimeMillis()) {
                return (String) session.getAttribute(getPrefix() + ":" + key);
            } else {
                throw new KaptchaTimeoutException();
            }
        } else {
            throw new KaptchaNotFoundException();
        }
    }

    @Override
    public void removeKaptcha(String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(getPrefix() + ":" + key);
    }
}
