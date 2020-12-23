package com.ysx.modules.service.impl;

import com.ysx.modules.domain.SysUser;
import com.ysx.modules.mapper.SysUserMapper;
import com.ysx.modules.service.SysUserService;
import com.ysx.utils.exception.ExceptionStatus;
import com.ysx.utils.exception.ServiceException;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.lang.StringUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-26
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	public List<SysUser> findSysUsers(String userName) {
		QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
		if (StringUtil.isNotBlank(userName)) {
			queryWrapper.like("user_name", userName);
		}
		
		return this.baseMapper.selectList(queryWrapper);
	}
	
	public SysUser getSysUserByUId(String userId) {
		if (ObjectUtil.isEmpty(userId)) {
			Assert.notNull(userId, "用户编码不能为空");
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
        return this.baseMapper.selectById(userId);
	}
	
	public SysUser getSysUserByUName(String userName) {
		if (StringUtil.isBlank(userName)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
		
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
        queryWrapper.eq("user_name", userName);
		return this.baseMapper.selectOne(queryWrapper);
	}
	
	public int insertUser(SysUser user) {
		String userName = user.getUserName();
		String password = user.getPassword();
		
		//查询用户名是否已存在
		SysUser sysuser = this.getSysUserByUName(userName);
		if (!ObjectUtil.isEmpty(sysuser)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg()+": 用户名已存在");
		}
		if (StringUtil.isBlank(userName)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg()+": 用户名不能为空");
		}
		if (StringUtil.isBlank(password)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg()+": 密码不能为空");
		}else if (password.length() < 6) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg()+": 密码不能少于6位");
		}else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        password = encoder.encode(password);
	        user.setPassword(password);
		}
		
		return this.baseMapper.insert(user);
	}

	public int saveUser(SysUser user) {
		if (ObjectUtil.isEmpty(user)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
		String id = user.getId();
		if (StringUtil.isNotBlank(id)) {
			//update
			QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
			queryWrapper.eq("id", user.getId());
			
			return this.baseMapper.update(user, queryWrapper);
		}
		return this.insertUser(user);
	}
	
	public int deleteUser(List<String> userIds) {
	
		return this.baseMapper.deleteBatchIds(userIds);
	}
	
}
