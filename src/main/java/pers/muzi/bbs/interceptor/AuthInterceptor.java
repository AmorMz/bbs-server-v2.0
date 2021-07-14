package pers.muzi.bbs.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.muzi.bbs.annotation.AdminRequired;
import pers.muzi.bbs.annotation.LoginRequired;
import pers.muzi.bbs.common.constant.RespCode;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.common.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AmorMz
 * 拦截请求 获取jwt验证身份
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 存放当前登录用户id
     */
    private static final ThreadLocal<Integer> USER_ID = new ThreadLocal<>();

    /**
     * 存放当前用户身份
     */
    private static final ThreadLocal<Integer> ROLE = new ThreadLocal<>();

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

        // 判断该请求处理方法是否存在LoginRequired注解
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
        // 请求Controller是否存在AdminRequired注解
        AdminRequired adminRequired = handlerMethod.getMethod().getDeclaringClass().getAnnotation(AdminRequired.class);

        System.out.println(adminRequired != null);
        if (loginRequired != null || adminRequired != null) {
            /*
             请求头中Authorization格式为 Bearer token
             如果不携带token get到的是字符串undefined
             */
            String bearer = request.getHeader("Authorization");
            if (!StringUtils.hasLength(bearer)) {
                error(response);
                return false;
            }

            // 去掉 Bearer和空格拿到token
            String token = bearer.substring(7);
            String undefined = "undefined";
            if ("".equals(token) || token.equalsIgnoreCase(undefined)) {
                error(response);
                return false;
            }

            /*
             工具类get信息之前会进行jwt验签 异常由统一异常进行处理
             获取当前登录用户id、role
             */
            Integer userId = JwtUtil.getIdByJWT(token);
            Integer role = JwtUtil.getRoleByJWT(token);
            USER_ID.set(userId);
            ROLE.set(role);

            // 管理员身份验证
            if (adminRequired != null && role == 0) {
                permissionError(response);
                return false;
            }

            return true;
        } else {
            // 无需登录 放行请求
            return true;
        }
    }


    private void error(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Resp resp = Resp
                .error()
                .code(RespCode.UNAUTHORIZED)
                .message("您还没有登录!或者登录过期，请重新登陆");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(resp));
    }

    private void permissionError(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Resp resp = Resp
                .error()
                .code(RespCode.FORBIDDEN)
                .message("权限不足，禁止访问");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(resp));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 运行结束 删除ThreadLocal
        USER_ID.remove();
        ROLE.remove();
    }

    public static Integer getId() {
        return USER_ID.get();
    }

    public static Integer getRole() {
        return ROLE.get();
    }
}
