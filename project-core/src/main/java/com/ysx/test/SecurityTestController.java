package com.ysx.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysx.modules.domain.SysUser;
import com.ysx.modules.service.SysUserService;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年12月2日
 */
@RestController
public class SecurityTestController {
	
	@Autowired
	SysUserService sysUserService;

	@GetMapping(value = "/home")
	public String hello() {
		return "Hello, spring security!";
	}
	
	@GetMapping(value = "/user/test")
	public String userTest() {
		return "Hello, user!";
	}
	
	@GetMapping(value = "/admin/test")
	public String adminTest() {
		return "Hello, admin!";
	}
	
	@GetMapping("/get/user")
	public void getSysUserByUName() {
		System.out.println(sysUserService.getSysUserByUName("user"));
	}
	
	@PostMapping("/insert/user")
	public void insertUser(@RequestBody SysUser user) {
		int a = sysUserService.insertUser(user);
		System.out.println(a);
	}
}
