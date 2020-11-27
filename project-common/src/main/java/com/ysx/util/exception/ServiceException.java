/**
 * 
 */
package com.ysx.util.exception;

/**
 * 
 * * <p>
 * 业务异常，这里约定每个业务场景的错误都是有一个错误码的。
 * </p>
 * <p>
 * 这个错误码需要展示给前端。前端需要根据这个码值做一些特殊的判断。如余额不足，
 * 错误码 001，那么前端根据这个码值引导用户去充值页面。
 * <p>
 *
 * @author yangShiXiong
 * @Data 2020年11月26日
 */
public class ServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	
	/**
	 * 构造一个业务异常类
	 * 
	 * @param code 错误码,注意约定的业务错误码都是大于600的
	 * @param message 错误信息
	 */
	public ServiceException(String code, String message){
		super(message);
		this.code = code;
	}
 
	/**
	 * 返回错误码
	 * @return 错误码
	 */
	public String getCode() {
		return code;
	}


}
