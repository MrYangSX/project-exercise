/**
 * 
 */
package com.ysx.util.Exception;

/**
 * 
 * 异常状态
 * 
 * @author yangShiXiong
 * @Data 2020年11月26日
 */
public enum ExceptionStatus {

	SUCCESS(1200, "操作成功"),
	SUCCESS_LOGGED(1203,"已登录"),
	ERROR(1500, "操作失败"),
	ERROR_LOGIN(1600, "登录失败"),
	ERROR_TOKEN(1602,"TOKEN失效或者错误"),
	ERROR_ARG(1601, "参数错误"),
	ERROR_AUTH(1606, "认证失败"), 
	ERROR_QUERY(1608, "数据丢失");


    private int code;
    private String msg;

    ExceptionStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
