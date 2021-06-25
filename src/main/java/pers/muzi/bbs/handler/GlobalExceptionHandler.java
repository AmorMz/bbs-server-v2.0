package pers.muzi.bbs.handler;

import io.jsonwebtoken.ClaimJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pers.muzi.bbs.common.constant.RespCode;
import pers.muzi.bbs.common.result.Resp;
import pers.muzi.bbs.exception.global.ParamException;

import java.util.Objects;

/**
 * @author AmorMz
 * 全局统一异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // -------------------参数校验-------------------

    /**
     * 参数校验统一处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Resp handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }

    /**
     * 参数错误异常处理
     */
    @ExceptionHandler(ParamException.class)
    public Resp handlerParamException(ParamException e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message(e.getMessage());
    }

    /**
     * RequestParam参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Resp handlerParamException(Exception e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message("缺失参数!");
    }

    /**
     * RequestParam参数类型不匹配
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Resp handlerParamException(MethodArgumentTypeMismatchException e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message("参数有误!");
    }


    // -------------------jwt异常-------------------

    /**
     * token出现问题 统统重新登录
     */
    @ExceptionHandler(ClaimJwtException.class)
    public Resp claimJwtExceptionHandler(ClaimJwtException e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .code(RespCode.UNAUTHORIZED)
                .message("登录已过期，请重新登录");
    }






    // -------------------未知异常-------------------
    /**
     * 未知异常处理 置底
     */
    @ExceptionHandler(Exception.class)
    public Resp globalException(Exception e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message("系统错误，请联系管理员");
    }
}
