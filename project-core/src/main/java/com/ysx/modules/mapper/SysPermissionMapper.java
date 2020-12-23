package com.ysx.modules.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysx.modules.domain.SysPermission;

/**
 * <p>
 *  权限Mapper 接口
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-12-21
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

	/**
	 * 
	 * todo : 查询用户对应权限信息
	 *
	 * @param userId
	 * @return
	 * @author yangShiXiong
	 * @Data 2020年12月21日
	 */
	public SysPermission getPermissionByUId(@Param("userId")String userId); 
}
