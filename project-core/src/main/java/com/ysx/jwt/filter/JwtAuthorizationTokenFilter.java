/**
 * 
 */
package com.ysx.jwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ysx.jwt.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

/** 
 * 
 *<p> <p> 
 *  
 * @author yangShiXiong  
 * @Data 2020年12月16日
 */
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter{

	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	private JwtTokenUtil jwtTokenUtil;
	private String tokenHeader;
	
	public JwtAuthorizationTokenFilter(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
											        JwtTokenUtil jwtTokenUtil, 
											        @Value("${jwt.token}") String tokenHeader) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.tokenHeader = tokenHeader;
	}
	
	/**
	 * jwt业务的拦截
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String requestHeader = request.getHeader(this.tokenHeader);
	    String username = null;
	    String authToken = null;
	    //.startsWith("Bearer ")  查看是否以指点字符串为前缀
	    if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
	    	authToken = requestHeader.substring(7);
		    try {
		    	username = jwtTokenUtil.getUsernameFromToken(authToken);
		    } catch (ExpiredJwtException e) {
		    }
	    }
	 
	    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
	 
	    	UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
	 
		    if (jwtTokenUtil.validateToken(authToken, userDetails)) {
			    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			    SecurityContextHolder.getContext().setAuthentication(authentication);
		    }
	    }
	    filterChain.doFilter(request, response);
	}

}
