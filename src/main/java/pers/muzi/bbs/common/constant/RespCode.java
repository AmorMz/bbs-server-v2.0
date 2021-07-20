package pers.muzi.bbs.common.constant;

/**
 * @Author AmorMz
 * 统一返回结果 状态码常量
 **/
public interface RespCode {

    /**
     * 操作成功
     */
    Integer SUCCESS = 20000;

    /**
     * 操作失败 产生错误
     */
    Integer ERROR = 50000;

    /**
     * 暂未登录
     */
    Integer UNAUTHORIZED = 40001;

    /**
     * 没有相应权限
     */
    Integer FORBIDDEN = 40003;

    /**
     * 请求不被处理
     */
    Integer REPEAT_REQUEST = 20001;


}
