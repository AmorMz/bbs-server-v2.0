package pers.muzi.bbs.exception;

/**
 * @author AmorMz
 * 用户有关异常
 */
public class UserException extends RuntimeException {
    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
