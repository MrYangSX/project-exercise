package com.ysx.security.interceptor.handler;

import com.ysx.utils.result.ResultReturn;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 *<p> 认证成功的处理 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月22日
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        new GalenWebMvcWrite().writeToWeb(response, ResultReturn.buildFailure(200, "登录成功!"));
        System.out.println("登录成功!");
    }
}
