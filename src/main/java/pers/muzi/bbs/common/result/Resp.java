package pers.muzi.bbs.common.result;

import lombok.Data;
import pers.muzi.bbs.common.constant.RespCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author AmorMz
 * 统一返回结果
 **/
@Data
public class Resp {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回消息通知
     */
    private String message;

    /**
     * 返回data容器
     */
    private Map<String, Object> data = new HashMap<>();


    private Resp() {

    }

    public static Resp ok() {
        Resp resp = new Resp();
        resp.setSuccess(true);
        resp.setCode(RespCode.SUCCESS);
        resp.setMessage("成功");
        return resp;
    }

    public static Resp error() {
        Resp resp = new Resp();
        resp.setSuccess(false);
        resp.setCode(RespCode.ERROR);
        resp.setMessage("失败");
        return resp;
    }

    public static Resp unauthorized() {
        Resp resp = new Resp();
        resp.setSuccess(false);
        resp.setCode(RespCode.UNAUTHORIZED);
        resp.setMessage("您还没有登录或token已过期，请重新登录");
        return resp;
    }

    public static Resp forbidden() {
        Resp resp = new Resp();
        resp.setSuccess(false);
        resp.setCode(RespCode.FORBIDDEN);
        resp.setMessage("权限不足");
        return resp;
    }

    public Resp success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Resp message(String message) {
        this.setMessage(message);
        return this;
    }

    public Resp code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Resp data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Resp data(Map<String, Object> map) {
        this.data.putAll(map);
        return this;
    }

}
