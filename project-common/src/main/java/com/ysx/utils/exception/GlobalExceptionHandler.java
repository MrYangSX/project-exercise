package com.ysx.utils.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysx.utils.result.ResultReturn;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 异常处理类
 * 全局异常处理器,处理新增的异常，对于有特殊逻辑的返回直接定义个新方法
 * 
 * @author yangShiXiong
 * @Data 2020年11月26日
 */

@ControllerAdvice
@Component
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

	//业务异常	
	@ExceptionHandler(ServiceException.class)
	public ResultReturn<String> handlerServiceException(ServiceException e){
		log.error("业务异常:{}",e.getMessage());
        return ResultReturn.buildFailure(Integer.getInteger(e.getCode()), e.getMessage());
	}
	
	//运行时异常
	public ResultReturn<String> processRuntimeException(RuntimeException e) {
        e.printStackTrace();
        log.error("运行时异常:{}",e.getMessage());
        return ResultReturn.buildFailure(ExceptionStatus.ERROR.getCode(), e.getMessage());
    }
	
	//系统异常
	@ExceptionHandler(Exception.class)
	public ResultReturn<String> handlerException(Exception e){
		log.error(e.getMessage(), e);
        return ResultReturn.buildFailure(500, e.getMessage());
	}

}
