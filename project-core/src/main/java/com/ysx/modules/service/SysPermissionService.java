package com.ysx.modules.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysx.modules.domain.SysPermission;

/**
 * <p>
 *  权限 服务类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-12-21
 */
public interface SysPermissionService extends IService<SysPermission> {

	/**
	 * 
	 * todo : 查询所有权限信息
	 *
	 * @param userId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
	public List<SysPermission> findPermissions(); 
	
	/**
	 * 
	 * todo : 查询用户对应权限信息
	 *
	 * @param userId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
	public SysPermission getPermissionByUId(String userId); 
	
	/**
	 * 
	 * todo : 保存权限信息
	 *
	 * @param sysPermission
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
	public int savePermission(SysPermission sysPermission);
	
	/**
	 * 
	 * todo : 删除权限信息 
	 *
	 * @param PermissionId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
	public int deletePermission(List<String> permissionIds);
}