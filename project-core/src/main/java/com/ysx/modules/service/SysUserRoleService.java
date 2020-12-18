package com.ysx.modules.service;

import java.util.List;

/**
 * 
 * 用户-角色信息 服务类
 * 
 * @author yangShiXiong
 * @Data 2020年11月27日
 */
public interface SysUserRoleService {

	/**
	 * 
	 * todo :根据角色id查询用户id 
	 *
	 * @param roleId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	String getUserIdByRId(String roleId);
	
	/**
	 * 
	 * todo :根据用户id查询角色id
	 *
	 * @param userId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	List<String> getRoleIdByUId(String userId);
	
	/**
	 * 
	 * todo : 保存用户-角色
	 *
	 * @param userId
	 * @param roleIds
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月10日
	 */
	public int save(String userId, List<String> roleIds);
}
