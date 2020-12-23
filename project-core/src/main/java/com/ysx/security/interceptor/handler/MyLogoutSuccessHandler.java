package com.ysx.security.interceptor.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ysx.utils.result.ResultReturn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 
 *<p> 注销登录处理 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月22日
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        new GalenWebMvcWrite().writeToWeb(response, ResultReturn.buildSuccess("注销成功!"));
//        System.out.println("注销成功!");
    }
}
