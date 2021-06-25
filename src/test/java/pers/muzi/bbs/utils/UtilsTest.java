package pers.muzi.bbs.utils;

import org.junit.jupiter.api.Test;
import pers.muzi.bbs.common.utils.CommonUtils;

/**
 * @author AmorMz
 */
public class UtilsTest {
    @Test
    public void saltTest() {
        System.out.println(CommonUtils.getSalt());
    }

    @Test
    public void strTest() {
        String str = "你好哈哈";
        System.out.println(str.substring(4));
    }
}
