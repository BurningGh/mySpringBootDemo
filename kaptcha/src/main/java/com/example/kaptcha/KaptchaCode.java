package com.example.kaptcha;

import lombok.Data;

import java.awt.image.BufferedImage;

/**
 * 验证码对象实体
 *
 * @author novel
 * @date 2019/12/2
 */
@Data
public class KaptchaCode {
    private BufferedImage codeBufferedImage;
    private String base64Image;
    private String key;
}
