package com.ysx.security.interceptor.handler;

import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.ysx.utils.result.ResultReturn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 *<p> 认证失败的处理 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月22日
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ResultReturn<?> resultReturn;
        if (exception instanceof BadCredentialsException ||
                exception instanceof UsernameNotFoundException) {
        	resultReturn = ResultReturn.buildFailure("账户名或者密码输入错误!");
        } else if (exception instanceof LockedException) {
        	resultReturn = ResultReturn.buildFailure("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
        	resultReturn = ResultReturn.buildFailure("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
        	resultReturn = ResultReturn.buildFailure("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
        	resultReturn = ResultReturn.buildFailure("账户被禁用，请联系管理员!");
        } else {
        	resultReturn = ResultReturn.buildFailure("登录失败!");
        }
        //response.setStatus(401);
        new GalenWebMvcWrite().writeToWeb(response, resultReturn);
    }
}
