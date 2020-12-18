package com.ysx.jwt.filter;
//package com.ysx.jwt;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//import io.jsonwebtoken.Jwts;
//
///** 
// * 
// *<p> <p> 
// *
// * token的校验
// *  
// * @author yangShiXiong  
// * @Data 2020年12月16日
// */
//public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
//
//	/**
//	 * @param authenticationManager
//	 */
//	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
//		super(authenticationManager);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
//	 * 如果校验通过，就认为这是一个取得授权的合法请求
//	 */
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String header = request.getHeader("Authorization");
//	 
//		if (header == null || !header.startsWith("Bearer ")) {
//			chain.doFilter(request, response);
//			return;
//		}
//	 
//		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//	 
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		chain.doFilter(request, response);
//	}
//	
//	
//	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//		  String token = request.getHeader("Authorization");
//		  if (token != null) {
//			  // parse the token.
//			  String user = Jwts.parser()
//			     .setSigningKey("MyJwtSecret")
//			     .parseClaimsJws(token.replace("Bearer ", ""))
//			     .getBody()
//			     .getSubject();
//		 
//			  if (user != null) {
//				  return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
//			  }
//			  return null;
//		 }
//		  return null;
//	}
//}
