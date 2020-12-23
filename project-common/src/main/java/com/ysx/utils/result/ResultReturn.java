/**
 * 
 */
package com.ysx.utils.result;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 返回结果
 * 
 * @author yangShiXiong
 * @param <T>
 * @Data 2020年11月26日
 */
@Data
public class ResultReturn<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 	编码
	 */
	private Integer code = 0;

	/**
	 * 	消息信息
	 */
	private String message;

	/**
	 *	 结果数据
	 */
	private T data;
	
	/**
	 *	是否成功
	 */
    private Boolean success = false;

	public ResultReturn() {
	}
	
	public ResultReturn(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.message = message;
        this.code = code;
        this.data = data;
    }
	
	public ResultReturn(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
	
	public ResultReturn(Boolean success, Integer code, T data) {
        this.success = success;
        this.code = code;
        this.data = data;
    }
	
	/**
	 * 	 构建返回结果
	 * @param success
	 * @param code
	 * @param message
	 * @param message_description
	 * @param data
	 * @return
	 */
	public static <T> ResultReturn<T> build(Boolean success, Integer code, String message, T data){
		return new ResultReturn<T>(success, code, message, data);
    }
	
	/**
	 * 	 构建返回结果
	 * @param success
	 * @param code
	 * @param message
	 * @param message_description
	 * @param data
	 * @return
	 */
	public static <T> ResultReturn<T> build(Boolean success, Integer code, String message){
		return new ResultReturn<T>(success, code, message);
    }
	
	/**
	 * 	 构建返回结果
	 * @param success
	 * @param code
	 * @param message
	 * @param message_description
	 * @param data
	 * @return
	 */
	public static <T> ResultReturn<T> build(Boolean success, Integer code, T data){
		return new ResultReturn<T>(success, code, data);
    }
	
    /**
     *	 构建成功结果
     * @param message
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildSuccess(String message, T data) {
        return build(Boolean.TRUE, 0, message, data);
    }
    
    /**
     * 	构建成功数据
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildSuccess(T data){
        return build(Boolean.TRUE, 0, data);
    }
    
    /**
     *	 构建失败消息、数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(Integer code, String message, T data){
        return build(Boolean.FALSE, code, message, data);
    }
    
    /**
     * 	构建失败消息
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(String message){
        return buildFailure(-1, message);
    }
    
    /**
     * 	构建失败消息
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(Integer code, String message){
        return buildFailure(code, message);
    }
}
