package pers.muzi.bbs.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author AmorMz
 * jwt工具类
 */
public class JwtUtil {
    /**
     * payload存放用户ID
     */
    private static final String JWT_USERID = "id";

    /**
     * payload存放角色
     */
    public static final String JWT_ROLE = "role";

    /**
     * 口令
     */
    private static final String JWT_SIGN = "@(MuziBBS!Author$AmorMz*)@";

    /**
     * 默认过期时间 1天
     * 单位毫秒
     */
    private static final long EXPIRATION_TIME = 86400000L;

    /**
     * 记住我默认过期时间 100天
     * 单位毫秒
     */
    private static final long REMEMBER_EXPIRATION_TIME = 8640000000L;


    /**
     * 生成token
     * header.payload.sing
     *
     * @param userId 用户id
     * @return token
     */
    public static String getToken(Integer userId, Integer role, Boolean rememberMe) {
        // 设置过期时间
        long expireTime = rememberMe ? REMEMBER_EXPIRATION_TIME : EXPIRATION_TIME;
        // 将id role 放入payload中
        Map<String, Object> map = new HashMap<>(16);
        map.put(JWT_USERID, userId);
        map.put(JWT_ROLE, role);
        // 生成token
        return Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, JWT_SIGN)
                .compact();
    }

    /**
     * 验证token合法性 无异常则验证通过 异常进行全局处理
     *
     * @param token token
     * @return Jws
     */
    private static Jws<Claims> verify(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SIGN)
                .parseClaimsJws(token);
    }

    /**
     * 从token中获取id
     *
     * @param token token
     * @return String 用户id
     */
    public static Integer getIdByJWT(String token) {
        Jws<Claims> jws = verify(token);

        // 不抛出异常则token有效 返回携带者用户id
        String str = jws.getBody().get(JWT_USERID).toString();
        return Integer.valueOf(str);
    }

    /**
     * 从token中获取role
     *
     * @param token token
     * @return 用户角色 0-普通用户 1-管理员admin
     */
    public static Integer getRoleByJWT(String token) {
        Jws<Claims> jws = verify(token);

        // 不抛出异常则token有效 返回携带者用户
        String str = jws.getBody().get(JWT_ROLE).toString();
        return Integer.valueOf(str);
    }

    public static long getExpirationTime() {
        return EXPIRATION_TIME;
    }

    public static long getRememberExpirationTime() {
        return REMEMBER_EXPIRATION_TIME;
    }
}
