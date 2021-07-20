package pers.muzi.bbs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AmorMz
 * 防止重复提交 默认2s内同一用户请求相同的url认为
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Commit {
    // 请求时间间隔
    long timeout() default 2L;
}
