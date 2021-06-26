package pers.muzi.bbs.exception;

/**
 * @author AmorMz
 * 参数有关异常
 */
public class ParamException extends RuntimeException {
    public ParamException() {
        super();
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
