package com.ysx.modules.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysx.modules.domain.SysRole;

/**
 * <p>
 * 角色信息 服务类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-27
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * 
	 * todo : 根据角色名称查询角色信息
	 *
	 * @param roleId
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	public SysRole getSysRoleByRName(String roleName);
	
	/**
	 * 
	 * todo : 根据角色id查询角色信息
	 *
	 * @param roleId
	 * @author yangShiXiong
	 * @Data 2020年11月27日
	 */
	public SysRole getSysRoleByRId(String roleId);
	
	/**
	 * 
	 * todo : 根据角色id集合查询角色信息
	 *
	 * @param roleIds
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月22日
	 */
	public List<SysRole> getSysRoleByRIds(List<String> roleIds);
	
	/**
	 * 
	 * todo : 保存角色
	 *
	 * @param role
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月10日
	 */
	public int saveSysRole(SysRole role);
	
	/**
	 * 
	 * todo : 根据权限id查询对应角色信息
	 *
	 * @param permissionId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月22日
	 */
	public List<SysRole> findSysRolesByPId(String permissionId);
}
