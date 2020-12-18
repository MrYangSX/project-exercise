package com.ysx.modules.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ysx.jwt.JwtTokenUtil;
import com.ysx.modules.domain.SysUser;
import com.ysx.modules.service.SysUserService;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.result.ResultReturn;

/**
 * 
 *登录 前端控制器
 * 
 * @author yangShiXiong
 * @Data 2020年12月11日
 */
@RestController
@RequestMapping("/sys")
public class SysController {
	
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService;
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	/**
	 * 
	 * todo : 登录 
	 *
	 * @param request
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月16日
	 */
	@PostMapping("/login")
	public ResultReturn<Object> login(@RequestBody SysUser sysUser, HttpServletRequest request) {
		if (ObjectUtil.isEmpty(sysUser)) {
			return ResultReturn.buildFailure("请填写用户名、密码");
		}
	    
	    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysUser.getUserName(),sysUser.getPassword());
	    Authentication authentication = authenticationManager.authenticate(authenticationToken);
	    if(ObjectUtil.isEmpty(authentication)) {
    		return ResultReturn.buildFailure("认证失败");
    	}
	    User user = (User) authentication.getPrincipal();
	    
	    Map<String, Object> data = new HashMap<String, Object>();
	    
        sysUser = sysUserService.getSysUserByUName(user.getUsername());
        data.put("username", sysUser.getUserName());
		data.put("user_id", sysUser.getId());
		
	    String token = jwtTokenUtil.generateToken(sysUser.getUserName());
	    data.put("access_token", token);
	    
//		data.put("expires_in", expiresIn);
		data.put("token_type", "Bearer ");
	    
	    return ResultReturn.buildSuccess("认证成功",data);
	}
	
	/**
	 * 
	 * todo : 注册
	 *
	 * @param user
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月16日
	 */
	@PostMapping("/signup")
	public ResultReturn<Object> saveUser(@RequestBody SysUser user) {
		
		int result = sysUserService.saveUser(user);
		if (result != 1) {
			return ResultReturn.buildFailure("操作失败");
		}
		return ResultReturn.buildSuccess("操作成功");
	}
}
