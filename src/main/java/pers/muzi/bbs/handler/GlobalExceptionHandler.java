package pers.muzi.bbs.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
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
     * 缺失参数异常处理
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Resp handlerParamException(MissingServletRequestParameterException e) {
        log.error(e.getMessage(), e);
        return Resp
                .error()
                .message("缺少参数");
    }

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
