package com.example.kaptcha.autoConfiguration;

import com.example.kaptcha.adapter.Kaptcha;
import com.example.kaptcha.adapter.impl.DefaultKaptchaImpl;
import com.example.kaptcha.adapter.impl.MathKaptchaImpl;
import com.example.kaptcha.cache.config.KaptchaCacheProperties;
import com.example.kaptcha.cache.impl.RedisKaptchaCache;
import com.example.kaptcha.cache.impl.SessionKaptchaCache;
import com.example.kaptcha.cache.manager.KaptchaCacheManager;
import com.example.kaptcha.cache.manager.RedisKaptchaCacheManagerImpl;
import com.example.kaptcha.cache.manager.SessionKaptchaCacheManagerImpl;
import com.example.kaptcha.config.*;
import com.example.kaptcha.creator.KaptchaTextCreator;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Properties;

/**
 * 验证码配置
 *
 * @author novel
 * @date 2019/9/29
 */
@Configuration
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaAutoConfiguration {

    /**
     * 验证码缓存注入
     */
    @EnableConfigurationProperties(KaptchaCacheProperties.class)
    @Configuration
    static class KaptchaCacheAutoConfiguration {

        /**
         * 默认使用session缓存
         */
        @ConditionalOnProperty(prefix = KaptchaCacheProperties.PREFIX, name = "type", havingValue = "session", matchIfMissing = true)
        @Configuration
        static class SessionKaptchaCacheManagerAutoConfiguration {

            @Bean
            public SessionKaptchaCache SessionKaptchaCache(KaptchaCacheProperties kaptchaCacheProperties) {
                return new SessionKaptchaCache(kaptchaCacheProperties);
            }

            @Primary
            @Bean
            public KaptchaCacheManager kaptchaCacheManager(SessionKaptchaCache sessionKaptchaCache) {
                return new SessionKaptchaCacheManagerImpl(sessionKaptchaCache);
            }
        }

        /**
         * redis 缓存注入
         */
        @ConditionalOnProperty(prefix = KaptchaCacheProperties.PREFIX, name = "type", havingValue = "redis")
        @Configuration
        @AutoConfigureAfter(StringRedisTemplate.class)
        static class RedisKaptchaCacheManagerAutoConfiguration {
            @Bean
            public RedisKaptchaCache redisKaptchaCache(StringRedisTemplate redisTemplate, KaptchaCacheProperties kaptchaCacheProperties) {
                return new RedisKaptchaCache(redisTemplate, kaptchaCacheProperties);
            }

            @Primary
            @Bean
            public KaptchaCacheManager kaptchaCacheManager(RedisKaptchaCache redisKaptchaCache) {
                return new RedisKaptchaCacheManagerImpl(redisKaptchaCache);
            }
        }
    }


    @Configuration
    @ConditionalOnProperty(prefix = KaptchaProperties.PREFIX, name = "type", havingValue = "default", matchIfMissing = true)
    @ConditionalOnClass(DefaultKaptcha.class)
    @AutoConfigureAfter(KaptchaCacheManager.class)
    static class DefaultKaptchaAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public DefaultKaptcha defaultKaptcha(KaptchaProperties properties) {
            Properties prop = new Properties();

            prop.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, String.valueOf(properties.getWidth()));
            prop.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, String.valueOf(properties.getHeight()));
            Content content = properties.getContent();
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, content.getSource());
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, String.valueOf(content.getLength()));
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, String.valueOf(content.getSpace()));

            BackgroundColor backgroundColor = properties.getBackgroundColor();
            prop.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, backgroundColor.getFrom());
            prop.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, backgroundColor.getTo());

            Border border = properties.getBorder();
            prop.setProperty(Constants.KAPTCHA_BORDER, border.getEnabled() ? "yes" : "no");
            prop.setProperty(Constants.KAPTCHA_BORDER_COLOR, border.getColor());
            prop.setProperty(Constants.KAPTCHA_BORDER_THICKNESS, String.valueOf(border.getThickness()));

            Font font = properties.getFont();
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, font.getNames());
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, String.valueOf(font.getSize()));
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, font.getColor().toString());

            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            defaultKaptcha.setConfig(new Config(prop));
            return defaultKaptcha;
        }


        @Bean
        @ConditionalOnMissingBean
        public Kaptcha kaptchaRender(DefaultKaptcha defaultKaptcha, KaptchaCacheManager kaptchaCacheManager, KaptchaProperties kaptchaProperties) {
            Kaptcha kaptcha = new DefaultKaptchaImpl(defaultKaptcha, kaptchaProperties);
            kaptcha.setCacheManager(kaptchaCacheManager);
            return kaptcha;
        }
    }

    @Configuration
    @ConditionalOnProperty(prefix = KaptchaProperties.PREFIX, name = "type", havingValue = "math")
    @ConditionalOnClass(KaptchaTextCreator.class)
    @AutoConfigureAfter(KaptchaCacheManager.class)
    static class KaptchaTextCreatorAutoConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public DefaultKaptcha getKaptchaBeanMath(KaptchaProperties properties) {
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            Properties prop = new Properties();
            prop.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, String.valueOf(properties.getWidth()));
            prop.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, String.valueOf(properties.getHeight()));

            Border border = properties.getBorder();
            prop.setProperty(Constants.KAPTCHA_BORDER, border.getEnabled() ? "yes" : "no");
            prop.setProperty(Constants.KAPTCHA_BORDER_COLOR, border.getColor());
            prop.setProperty(Constants.KAPTCHA_BORDER_THICKNESS, String.valueOf(border.getThickness()));

            Font font = properties.getFont();
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, font.getNames());
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, String.valueOf(font.getSize()));
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, font.getColor());

            // 验证码文本生成器
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_IMPL, KaptchaTextCreator.class.getName());

            Content content = properties.getContent();
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, content.getSource());
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, String.valueOf(content.getLength()));
            prop.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, String.valueOf(content.getSpace()));

            Noise noise = properties.getNoise();
            // 验证码噪点颜色 默认为Color.BLACK
            prop.setProperty(Constants.KAPTCHA_NOISE_COLOR, noise.getColor());
            // 干扰实现类
            prop.setProperty(Constants.KAPTCHA_NOISE_IMPL, noise.getImpl());

            Obscurificator obscurificator = properties.getObscurificator();
            // 图片样式 水纹com.google.code.kaptcha.impl.WaterRipple 鱼眼com.google.code.kaptcha.impl.FishEyeGimpy 阴影com.google.code.kaptcha.impl.ShadowGimpy
            prop.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, obscurificator.getImpl());
            Config config = new Config(prop);
            defaultKaptcha.setConfig(config);
            return defaultKaptcha;
        }

        @Bean
        @ConditionalOnMissingBean
        public Kaptcha kaptchaRender(DefaultKaptcha defaultKaptcha, KaptchaCacheManager kaptchaCacheManager, KaptchaProperties kaptchaProperties) {
            Kaptcha kaptcha = new MathKaptchaImpl(defaultKaptcha, kaptchaProperties);
            kaptcha.setCacheManager(kaptchaCacheManager);
            return kaptcha;
        }
    }
}
