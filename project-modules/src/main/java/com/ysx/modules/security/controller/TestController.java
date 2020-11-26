package com.ysx.modules.security.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ysx.modules.security.domain.Test;
import com.ysx.modules.security.service.impl.TestServiceImpl;

/**
 * <p>
 * 测试 前端控制器
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-25
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	TestServiceImpl testServiceImpl;
	
	@GetMapping("/test01")
	public void test() {
		System.out.println("test01");
	}
	
	@GetMapping("/test02")
	public void test2() {
		List<Test> data = testServiceImpl.findMpTests();
		System.out.println(data);
	}
}
