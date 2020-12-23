package com.ysx.modules.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysx.modules.domain.SysUser;
import com.ysx.modules.service.SysUserService;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.result.ResultReturn;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

	@Resource
	private SysUserService sysUserService;
	
	/**
	 * 
	 * todo : 用户分页列表
	 *
	 * @param userName(模糊查询字段)
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月23日
	 */
	@GetMapping("/find")
	public ResultReturn<Object> findSysUsers(String userName, Integer pageNum, Integer pageSize) {
		
		if (ObjectUtil.isEmpty(pageNum)) {
			pageNum = 1;
		}
		if (ObjectUtil.isEmpty(pageSize)) {
			pageSize = 10;
		}
		
		PageHelper.startPage(pageNum, pageSize);
		List<SysUser> list = sysUserService.findSysUsers(userName);
		
		return ResultReturn.buildSuccess(new PageInfo<>(list));
	}
	
	/**
	 * 
	 * todo : 新增用户
	 *
	 * @param user
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月9日
	 */
	@PostMapping("/insert")
	public ResultReturn<Object> insertUser(SysUser user) {
		
		sysUserService.insertUser(user);
		return ResultReturn.buildSuccess("操作成功");
	}
	
	/**
	 * 
	 * todo : 更新用户
	 *
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月9日
	 */
	@PostMapping("/save")
	public ResultReturn<Object> saveUser(@RequestBody SysUser user) {
		
		int result = sysUserService.saveUser(user);
		if (result != 1) {
			return ResultReturn.buildFailure("操作失败");
		}
		return ResultReturn.buildSuccess("操作成功");
	}
	
}

