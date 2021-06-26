package pers.muzi.bbs.common.utils;

import com.github.pagehelper.util.StringUtil;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @author AmorMz
 * 工具包
 */
public class CommonUtils {


    /**
     * 获取10位密码盐
     *
     * @return 截取前十位UUID
     */
    public static String getSalt() {
        return UUID
                .randomUUID()
                .toString()
                .replaceAll("-", "")
                .substring(0, 10);
    }

    /**
     * MD5加密字符串str
     *
     * @param str 原字符串
     * @return 加密后串
     */
    public static String MD5(String str) {
        if (!StringUtils.hasLength(str)) {
            return null;
        }

        return DigestUtils.md5DigestAsHex(str.getBytes());
    }
}
