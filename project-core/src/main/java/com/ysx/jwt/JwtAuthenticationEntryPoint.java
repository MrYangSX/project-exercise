/**
 * 
 */
package com.ysx.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/** 
 * 
 *<p>发现没有凭证 <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月16日
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("JwtAuthenticationEntryPoint:"+authException.getMessage());
	    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
	}

}
