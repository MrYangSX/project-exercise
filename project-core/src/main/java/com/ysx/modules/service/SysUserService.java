package com.ysx.modules.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysx.modules.domain.SysUser;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-26
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * 
	 * todo : 根据用户id查询用户信息
	 *
	 * @param userName
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	public SysUser getSysUserByUId(String userId);
	
	/**
	 * 
	 * todo : 根据用户名称查询用户信息
	 *
	 * @param userName
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	public SysUser getSysUserByUName(String userName);
	
	/**
	 * 
	 * todo : 新增用户
	 *
	 * @param user
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月9日
	 */
	public int insertUser(SysUser user);
	
	/**
	 * 
	 * todo : 保存用户
	 *
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月9日
	 */
	public int saveUser(SysUser user);
	
	/**
	 * 
	 * todo : 用户分页列表
	 *
	 * @param userName（模糊查询）
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月18日
	 */
	public List<SysUser> findSysUsers(String userName);
}
