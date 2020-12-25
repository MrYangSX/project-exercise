package com.ysx.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.ysx.utils.ServletUtils;
import com.ysx.utils.lang.StringUtil;
import com.ysx.utils.result.ResultReturn;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * <p>
 * 配置
 * <p>
 * 
 * @author yangShiXiong
 * @Data 2020年12月25日
 */
@Slf4j
public class SecurityHandlerConfig {

	/**
	 * 认证失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {

		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {

				String message = authException.getMessage();
				if (authException instanceof InsufficientAuthenticationException) {
					if (authException.getMessage().indexOf("Full") == 0) {
						message = "认证失败，身份信息未通过！";
					} else {
						message = "认证失败，信息错误或已过期！";
					}
				}
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json;charset=UTF-8");

				log.warn("认证失败，无法访问系统资源：{}", request.getRequestURI());

				ResultReturn<Object> result = ResultReturn.buildFailure(HttpServletResponse.SC_UNAUTHORIZED, message);
				ServletUtils.writeToWeb(response, result);
			}
		};
	}

	/**
	 * 无权限
	 * 
	 * @return
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {

		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				String message = accessDeniedException.getMessage();
				if (StringUtil.isBlank(message)) {
					message = "认证失败，操作权限不足！";
				}
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				response.setContentType("application/json;charset=UTF-8");

				log.warn("无权限，无法访问系统资源：{}", request.getRequestURI());

				ResultReturn<Object> result = ResultReturn.buildFailure(HttpServletResponse.SC_FORBIDDEN, message);
				ServletUtils.writeToWeb(response, result);
			}
		};
	}
	/*
	*//**
		 * 登录成功
		 * 
		 * @return
		 *//*
			 * @Bean public AuthenticationSuccessHandler loginSuccessHandler() {
			 * 
			 * log.info("进入登录成功跳转");
			 * 
			 * return new AuthenticationSuccessHandler() {
			 * 
			 * @Override public void onAuthenticationSuccess(HttpServletRequest request,
			 * HttpServletResponse response, Authentication authentication) throws
			 * IOException, ServletException {
			 * 
			 * User userDetails = (User)authentication.getPrincipal();
			 * 
			 * log.info("登录用户user:" + userDetails.getUsername() +
			 * "login"+request.getContextPath());
			 * 
			 * RequestCache cache = new HttpSessionRequestCache(); SavedRequest savedRequest
			 * = cache.getRequest(request, response);
			 * System.out.println(savedRequest.getRedirectUrl()); String url = "";
			 * if((savedRequest==null)){ url = "/sys/succeed"; }else{ url =
			 * savedRequest.getRedirectUrl(); }
			 * 
			 * log.info("登录成功跳转url ： {}" + url);
			 * 
			 * // super.onAuthenticationSuccess(request, response, authentication);
			 * response.setContentType("application/json;charset=UTF-8");
			 * response.sendRedirect(url); } }; }
			 */

	/**
	 * 登出成功
	 */
	@Bean
	public LogoutSuccessHandler logoutSuccessHandler() {

		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {

				log.info("注销成功!");
				response.setContentType("application/json;charset=UTF-8");
				response.sendRedirect("/sys/login/logout");
			}
		};
	}

	/**
	 * 认证失败
	 */
	public AuthenticationFailureHandler authenticationFailureHandler() {

		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				response.setContentType("application/json;charset=utf-8");

				ResultReturn<?> resultReturn;
				if (exception instanceof BadCredentialsException || exception instanceof UsernameNotFoundException) {
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

				log.warn("认证失败，无法访问系统资源：{}", exception.getMessage());

				ServletUtils.writeToWeb(response, resultReturn);
			}
		};

	}

}
