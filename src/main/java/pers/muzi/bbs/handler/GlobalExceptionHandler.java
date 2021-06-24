package pers.muzi.bbs.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pers.muzi.bbs.common.result.Resp;

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
        log.error(e.toString() + ":" + e.getMessage(), e);
        return Resp
                .error()
                .message(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
    }
}
