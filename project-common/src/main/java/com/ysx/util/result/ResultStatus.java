/**
 * 
 */
package com.ysx.util.result;

/**
 * 
 * 返回状态、描述
 * 
 * @author yangShiXiong
 * @Data 2020年11月26日
 */
public enum ResultStatus {

    UNKNOWN_ERROR(-1,"未知错误"), 	
    SUCCESS(200,"成功"),
    SERVER_INTERNAL_ERROR(500,"服务器内部错误"),
    RESOURCE_NOT_FOUND(404,"资源未找到"),
    PARAMETER_NOT_VALID(400,"参数不合法"),
    ;
    private Integer code;
    private String msg;

    ResultStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
