package pers.muzi.bbs.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.muzi.bbs.annotation.Commit;
import pers.muzi.bbs.common.constant.RespCode;
import pers.muzi.bbs.common.utils.InterceptorUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author AmorMz
 * 拦截包含@Commit注解的请求
 */
public class CommitInterceptor implements HandlerInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 嗅探请求方法
     */
    private static final String OPTIONS = "OPTIONS";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行嗅探请求
        if (request.getMethod().equalsIgnoreCase(OPTIONS)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Commit commit = handlerMethod.getMethodAnnotation(Commit.class);
        if (commit == null) {
            // 不存在注解 放行请求
            return true;
        }


        // 获取注解规定过期时间 用户ID+请求URI
        long timeout = commit.timeout();
        Integer userId = AuthInterceptor.getId();
        String redisKey = "Commit:" + userId + ":" + request.getMethod() + request.getRequestURI();

        // 查询redis是否存在key
        Boolean hasKey = stringRedisTemplate.hasKey(redisKey);

        if (hasKey) {
            // 存在key 拦截请求
            InterceptorUtils.error(response, "您的操作太快啦！休息一下吧", RespCode.REPEAT_REQUEST) ;
            return false;
        }

        // 将用户id+请求URI放入redis 并设置过期时间
        stringRedisTemplate.opsForValue().set(redisKey, "MUCH TOO", timeout, TimeUnit.SECONDS);
        return true;
    }
}
