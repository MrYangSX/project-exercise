package com.ysx.modules.service.impl;

import com.ysx.modules.domain.SysPermission;
import com.ysx.modules.mapper.SysPermissionMapper;
import com.ysx.modules.service.SysPermissionService;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.lang.StringUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  权限服务实现类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-12-21
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

	public List<SysPermission> findPermissions() {
		
		return this.baseMapper.selectList(null);
	}

	public SysPermission getPermissionByUId(String userId) {
		
		return this.baseMapper.getPermissionByUId(userId); 
	}

	public int savePermission(SysPermission sysPermission) {
		if (!ObjectUtil.isEmpty(sysPermission)) {
			String id = sysPermission.getId();
			if (StringUtil.isNotBlank(id)) {
				this.baseMapper.updateById(sysPermission);
			}
			
			this.baseMapper.insert(sysPermission);
		}
		return 0;
	}

	public int deletePermission(List<String> permissionIds) {
		if (permissionIds.size() > 0) {
			return this.baseMapper.deleteBatchIds(permissionIds);	
		}
		return 0;
	}
}
