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
	 *	 消息描述
	 */
	private String message_description;

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
	
	public ResultReturn(Boolean success, Integer code, String message, String message_description, T data) {
        this.success = success;
        this.message = message;
        this.message_description = message_description;
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
	public static <T> ResultReturn<T> build(Boolean success, Integer code, String message, String message_description, T data){
		return new ResultReturn<T>(success, code, message, message_description, data);
    }
	
    /**
     *	 构建成功结果
     * @param message
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildSuccess(String message, T data) {
        return build(Boolean.TRUE, 0, message, null, data);
    }
    
    /**
     * 	构建成功数据
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildSuccess(T data){
        return buildSuccess(null, data);
    }
    
    /**
     * 	构建成功消息
     * @param message
     * @return
     */
    public static <T> ResultReturn<T> buildSuccess(String message){
        return buildSuccess(message, null);
    }
    
    /**
     * 	构建失败结果
     * @param code
     * @param message
     * @param message_description
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(Integer code, String message, String message_description){
        return build(Boolean.FALSE, code, message, message_description, null);
    }
    
    /**
     *	 构建失败消息、数据
     * @param code
     * @param message
     * @param data
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(Integer code, String message, T data){
        return build(Boolean.FALSE, code, message, null, data);
    }
    
    /**
     * 	构建失败消息
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(Integer code, String message){
        return build(Boolean.FALSE, code, message, null, null);
    }
    
    /**
     * 	构建失败消息
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultReturn<T> buildFailure(String message){
        return build(Boolean.FALSE, null, message, null, null);
    }
}
