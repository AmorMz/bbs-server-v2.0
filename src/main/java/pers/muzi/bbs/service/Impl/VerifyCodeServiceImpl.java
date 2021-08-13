package pers.muzi.bbs.service.Impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import pers.muzi.bbs.common.utils.CommonUtils;
import pers.muzi.bbs.common.utils.VerifyCodeUtils;
import pers.muzi.bbs.entity.VerifyCode;
import pers.muzi.bbs.service.VerifyCodeService;
import java.util.HashMap;
import java.util.Map;


import java.util.concurrent.TimeUnit;

/**
 * @author AmorMz
 */
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 获取验证码BASE64编码
     * @return BASE64编码后验证码图片
     */
    @Override
    public Map<String, String> getVerifyCodeBase64() {
        Map<String, String> map = new HashMap<>(2);
        // 设置长宽
        VerifyCode verifyCode = VerifyCodeUtils.generate(80, 36);

        // uuid作为key
        String uuid = CommonUtils.UUID();
        // 验证码存入Redis 设置过期时间1分钟
        stringRedisTemplate
                .opsForValue()
                .set("verifyCode:" + uuid, verifyCode.getCode(), 60, TimeUnit.SECONDS);

        // 图片编码为Base64
        String codeBase64 = "data:image/png;base64," +
                VerifyCodeUtils.getVerifyCodeBase64(verifyCode.getImgBytes());

        // 返回图片base64编码 本次验证码请求唯一标识uuid
        map.put("codeBase64", codeBase64);
        map.put("uuid", uuid);
        return map;
    }

    /**
     * 验证码校验
     * @param UUID 验证码在Redis中的key
     * @param verifyCode 输入的验证码
     */
    @Override
    public boolean verify(String UUID, String verifyCode) {
        if (StringUtils.isBlank(UUID)) {
            // 删除记录
            stringRedisTemplate.delete(UUID);
            return false;
        }

        String redisKey = "verifyCode:" + UUID;
        String realCode = stringRedisTemplate.opsForValue().get(redisKey);
        // 验证与redis中的验证码是否相同
        boolean result = verifyCode.equalsIgnoreCase(realCode);
        // 无论正确与否 统统删除 前端自动请求刷新验证码
        stringRedisTemplate.delete(UUID);
        return result;
    }
}
