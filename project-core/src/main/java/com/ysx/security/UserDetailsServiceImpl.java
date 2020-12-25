package com.ysx.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ysx.modules.domain.SysRole;
import com.ysx.modules.domain.SysUser;
import com.ysx.modules.service.SysRoleService;
import com.ysx.modules.service.SysUserRoleService;
import com.ysx.modules.service.SysUserService;
import com.ysx.utils.lang.ObjectUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年11月30日
 */
@Slf4j
@Configuration
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	/**
	 * 授权的时候是对角色授权，认证的时候应该基于资源，而不是角色，因为资源是不变的，而用户的角色是会变的
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("进入Security认证");
		
		//查询user
		SysUser sysUser = sysUserService.getSysUserByUName(username);
		if (ObjectUtil.isEmpty(sysUser)) {
			throw new UsernameNotFoundException("用户不存在");
		}
		
		//role集合
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		//获取rId
		List<String> rIds = sysUserRoleService.getRoleIdByUId(sysUser.getId());
		if (!ObjectUtil.isEmpty(rIds)) {
			for (String rId : rIds) {
				SysRole sysRole = sysRoleService.getSysRoleByRId(rId);
				authorities.add(new SimpleGrantedAuthority(sysRole.getRoleNameEn()));
			}
		}
		/**
		 * User
		 * (包含：// 权限信息
	     *	Collection<? extends GrantedAuthority> getAuthorities();
	     *  // 密码信息
		 *  String getPassword();
		 *  // 登录用户名
		 *  String getUsername();
		 *  // 帐户是否过期
		 *  boolean isAccountNonExpired();
		 *  // 帐户是否被冻结
		 *  boolean isAccountNonLocked();
		 *  // 帐户密码是否过期，一般有的密码要求性高的系统会使用到，比较每隔一段时间就要求用户重置密码
		 *  boolean isCredentialsNonExpired();
		 *  // 帐号是否可用
		 *  boolean isEnabled();)
		 */
		// 返回UserDetails实现类(添加角色完成)
		User user = new User(sysUser.getUsername(), sysUser.getPassword(), authorities);
		
		log.info("Security认证成功");
		return user;
	}

}
