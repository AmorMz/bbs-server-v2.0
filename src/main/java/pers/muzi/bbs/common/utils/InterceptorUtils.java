package pers.muzi.bbs.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.muzi.bbs.common.constant.RespCode;
import pers.muzi.bbs.common.result.Resp;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author AmorMz
 */
public class InterceptorUtils {
    public static void error(HttpServletResponse response, String message, Integer code) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Resp resp = Resp
                .error()
                .code(code)
                .message(message);
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(resp));
    }

    public static void permissionError(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        Resp resp = Resp
                .error()
                .code(RespCode.FORBIDDEN)
                .message("权限不足，禁止访问");
        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().println(objectMapper.writeValueAsString(resp));
    }
}
