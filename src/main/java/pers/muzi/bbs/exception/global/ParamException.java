package pers.muzi.bbs.exception.global;

/**
 * @author AmorMz
 * 控制器接受参数错误
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
