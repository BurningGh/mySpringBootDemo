package com.example.kaptcha;

import com.example.kaptcha.adapter.Kaptcha;
import com.example.kaptcha.exception.KaptchaIncorrectException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试接口
 *
 * @author novel
 * @date 2019/12/26
 */
@RestController
public class CommonController {

    @Resource
    private Kaptcha kaptcha;

    /**
     * 验证码生成
     *
     * @return 验证码
     */
    @RequestMapping(value = "/captchaImageBase64")
    public Map<String, Object> getKaptchaImageBase64() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", kaptcha.writeBase64Code());
        map.put("result", true);
        return map;
    }

    /**
     * 验证码生成<br/>
     * 验证码的key存在于response的header里面
     *
     * @return 验证码
     */
    @RequestMapping(value = "/captchaImage")
    public void getKaptchaImage(HttpServletResponse response) {
        String key = kaptcha.writeImageCode(response);
    }

    @RequestMapping("/validated")
    public Map<String, Object> validated(String key, String code) {
        if (!kaptcha.validate(key, code)) {
            throw new KaptchaIncorrectException("验证码不正确");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("result", true);
        return map;
    }
}
