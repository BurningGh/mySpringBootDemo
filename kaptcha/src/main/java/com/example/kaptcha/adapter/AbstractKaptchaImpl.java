package com.example.kaptcha.adapter;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import com.example.kaptcha.KaptchaCode;
import com.example.kaptcha.cache.manager.KaptchaCacheManager;
import com.example.kaptcha.config.KaptchaProperties;
import com.example.kaptcha.exception.KaptchaException;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 默认随机验证码实现类
 *
 * @author novel
 * @date 2019/12/2
 */
public abstract class AbstractKaptchaImpl implements Kaptcha {
    /**
     * 验证码产生器
     */
    protected final DefaultKaptcha kaptcha;
    /**
     * 验证码缓存管理器
     */
    protected KaptchaCacheManager kaptchaCacheManager;
    /**
     * 验证码配置
     */
    protected final KaptchaProperties kaptchaProperties;

    public AbstractKaptchaImpl(DefaultKaptcha kaptcha, KaptchaProperties kaptchaProperties) {
        this.kaptcha = kaptcha;
        this.kaptchaProperties = kaptchaProperties;
    }


    @Override
    public KaptchaCode render() {
        String uuid = IdUtil.simpleUUID();
        BufferedImage image = render(uuid);
        KaptchaCode kaptchaCode = new KaptchaCode();
        kaptchaCode.setCodeBufferedImage(image);
        kaptchaCode.setKey(uuid);
        return kaptchaCode;
    }

    @Override
    public String writeImageCode(HttpServletResponse response) {
        KaptchaCode kaptchaCode = render();
        ServletOutputStream out = null;
        try {
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("key", kaptchaCode.getKey());
            response.setContentType("image/jpeg");

            out = response.getOutputStream();
            ImageIO.write(kaptchaCode.getCodeBufferedImage(), "jpg", out);
            out.flush();
        } catch (Exception e) {
            throw new KaptchaException(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {
            }
        }
        return kaptchaCode.getKey();
    }

    @Override
    public KaptchaCode writeBase64Code() {
        KaptchaCode kaptchaCode = render();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(kaptchaCode.getCodeBufferedImage(), "jpg", outputStream);
            kaptchaCode.setBase64Image("data:image/jpg;base64," + Base64.encode(outputStream.toByteArray()));
            kaptchaCode.setCodeBufferedImage(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return kaptchaCode;
    }


    @Override
    public void setCacheManager(KaptchaCacheManager kaptchaCacheManager) {
        this.kaptchaCacheManager = kaptchaCacheManager;
    }
}
