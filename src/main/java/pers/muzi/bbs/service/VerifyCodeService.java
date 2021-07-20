package pers.muzi.bbs.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author AmorMz
 * 验证码
 */
public interface VerifyCodeService {

    /**
     * 获取验证码BASE64编码
     *
     * @return BASE64编码后验证码图片
     */
    Map<String, String> getVerifyCodeBase64();

    /**
     * 验证码校验
     *
     * @param UUID redisKey
     * @param verifyCode 输入的验证码
     * @return 校验结果
     */
    boolean verify(String UUID, String verifyCode);
}
