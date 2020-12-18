package com.ysx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ysx.jwt.JwtAuthorizationTokenFilter;


/**
 * 
 * Security配置类
 * 
 * @author yangShiXiong
 * @Data 2020年11月26日
 */
//配置类
@Configuration
//开启 Security 服务
@EnableWebSecurity
//开启全局 Securtiy 注解
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Autowired
//	UserDetailsServiceImpl userDetailsService;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;
	
	@Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }
	/**
     * 放行接口
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //拦截忽略的地方,认证信息不会注入
        web.ignoring().antMatchers( "/test/**");
//        web.ignoring().antMatchers( "/sys/login");
    }
	
	@Bean
	@Qualifier("authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	
	/**
     *封装身份认证提供者
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());  //自定义的用户和角色数据提供者
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder); //设置密码加密对象
        return authenticationProvider;
    }
    
	/**
	 * 安全认证
	 */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		内存中获取用户名和密码
//        auth.inMemoryAuthentication().withUser("user1").password("password").roles("");
//		从数据库读取用户名和密码
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
		//设置身份认证提供者
        auth.authenticationProvider(authenticationProvider()); 
    }
	
	/**
	 * 链路管理
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 路径配置
		http
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/sys/login").permitAll()
			.antMatchers(HttpMethod.POST, "/sys/signup").permitAll()
			.anyRequest().authenticated()//表示剩余的其他接口，登录之后就能访问
			.and()
            .csrf().disable()// 禁用 Spring Security 自带的跨域处理
			.formLogin().and()
			.httpBasic()
			.and()
			.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//		    .addFilter(new JWTLoginFilter(authenticationManager()))
//		    .addFilter(new JWTAuthenticationFilter(authenticationManager()));
				//配置路径拦截，表明路径访问所对应的权限，角色，认证信息。
//				.authorizeRequests()
//		        .antMatchers("/", "/api/home").permitAll()
//		        .antMatchers("/api/sys/admin/**").hasRole("ROLE_ADMIN")
//		        .antMatchers("/api/sys/user/**").hasRole("ROLE_USER")
//		        .anyRequest().authenticated()
//		        .and()
//		    .formLogin()
//		    	//指定“/login”该路径为登录页面
//		    	//当未认证的用户尝试访问任何受保护的资源时，都会跳转到“/login”。
//		        .loginPage("/login")
//		        //指定“/error”为错误页面，当有异常抛出时，跳转到“/error”。
//		        .failureUrl("/error")
//		        .permitAll()
//		        .and()
//	        //默认指定“/logout”为注销页面。
//		    .logout()
//		        .permitAll();	
			//禁用 csrf
//			.csrf()
//				.disable()
//				.cors()
//				.and()
//			.authorizeRequests()
////				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//				//  /login 允许所有人访问
//				.antMatchers(HttpMethod.POST, "/login").permitAll()
//				.anyRequest().authenticated()
//				.and()
//			// 定义当需要用户登录时候，转到的登录页面
//			.formLogin()
//				.and()
//			//禁用http-basic方式(认证弹窗)
//			.httpBasic().disable()
//			.logout().permitAll();
	}
	
}
