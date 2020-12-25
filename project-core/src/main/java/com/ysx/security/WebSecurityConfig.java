package com.ysx.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
@Import(SecurityHandlerConfig.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;
	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
//	@Autowired
//	AccessDeniedHandler accessDeniedHandler;
//	@Autowired
//	AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	@Autowired
	private DataSource dataSource;
	
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
    	web.ignoring().antMatchers( "/sys/login");
    	//对静态资源放行
        web.ignoring().antMatchers("/static/**");
        
    }
	
	@Bean
	@Qualifier("authenticationManager")
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
	    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
	    tokenRepository.setDataSource(dataSource);
	    
	    // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
	    //tokenRepository.setCreateTableOnStartup(true);
	    return tokenRepository;
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
		//路径配置
		http
			//无权限
//	        .exceptionHandling().accessDeniedHandler(accessDeniedHandler).and()
	        //认证失败
	        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
	        
		http.authorizeRequests()
			//允许匿名的url
			.antMatchers("/sys/login","/login.html").permitAll()
			.antMatchers("/sys/login/**").permitAll()
//			.antMatchers(HttpMethod.POST, "/sys/login/authentication").permitAll()
			//表示剩余的其他接口，登录之后就能访问
			.anyRequest().authenticated();
		
		//自动登录（记住我）
//            .and().rememberMe()
		
		//设置登陆/出页
		http.formLogin()
			.loginPage("/sys/login").permitAll()
//			.successHandler(authenticationSuccessHandler)
			//指定自定义form表单请求的路径
//			.loginProcessingUrl("http://localhost:8000/api/sys/login/authentication")
			//设置登陆成功页
//			.defaultSuccessUrl("/sys/succeed")
//			.successForwardUrl("/sys/succeed")
			//登出
			.and()
			.logout().logoutUrl("/sys/logout")	.logoutSuccessHandler(logoutSuccessHandler).permitAll()
			
			.and()
			.httpBasic()
			//添加jwt过滤
			.and()
			.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);		 
        
		//基于token，所以不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//禁用缓存
        http.headers().cacheControl();
		//关闭CSRF跨域 禁用 Spring Security 自带的跨域处理
	    http.csrf().disable();
	}
	
}
