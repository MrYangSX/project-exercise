package com.ysx.modules.service.impl;

import com.ysx.modules.domain.SysRole;
import com.ysx.modules.mapper.SysRoleMapper;
import com.ysx.modules.service.SysRoleService;
import com.ysx.utils.exception.ExceptionStatus;
import com.ysx.utils.exception.ServiceException;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.lang.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息 服务实现类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-27
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	public SysRole getSysRoleByRName(String roleName) {
		if (StringUtil.isBlank(roleName)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
        queryWrapper.eq("role_name", roleName);
		return this.baseMapper.selectOne(queryWrapper);
	}
	
	public SysRole getSysRoleByRId(String roleId) {
		if (ObjectUtil.isEmpty(roleId)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
		return this.baseMapper.selectById(roleId);
	}
	
	public List<SysRole> getSysRoleByRIds(List<String> roleIds) {
		if (roleIds.size() > 0) {
			
			QueryWrapper<SysRole> queryWrapper = new QueryWrapper<SysRole>();
			queryWrapper.in("role_id",roleIds);
			
			return this.baseMapper.selectList(queryWrapper);
		}
		return null;
	}
	
	public int saveSysRole(SysRole role) {
		String roleId = role.getId();
		String roleName = role.getRoleNameCn();
		
		if (StringUtil.isBlank(roleName)) {
			throw new ServiceException(ExceptionStatus.ERROR_ARG.getCode()+"", ExceptionStatus.ERROR_ARG.getMsg());
		}
		if (StringUtil.isNotBlank(roleId)) {
			SysRole r = this.getSysRoleByRId(roleId);
			if (ObjectUtil.isEmpty(r)) {
				throw new ServiceException(ExceptionStatus.ERROR.getCode()+"", ExceptionStatus.ERROR.getMsg());
			}else {
				//update
				QueryWrapper<SysRole> updateWrapper = new QueryWrapper<SysRole>();
				updateWrapper.eq("id", roleId);
				
				return this.baseMapper.update(role, updateWrapper);
			}
		}
		
		//insert
		return this.baseMapper.insert(role);
	}
	
	public List<SysRole> findSysRolesByPId(String permissionId){
		List<SysRole> list = new ArrayList<SysRole>();
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("SELECT r.id AS id, r.role_name_cn AS roleNameCn, r.role_name_en AS roleNameEn");
		sBuffer.append("FROM sys_role r ");
		sBuffer.append("LEFT JOIN sys_permission_role pr ON r.id = pr.role_id ");
		sBuffer.append("WHERE pr.permission_id = ?");
		
		List<Record> records = Db.find(sBuffer.toString(), permissionId);
		if (records.size() > 0) {
			for (Record r : records) {
				SysRole sysRole = new SysRole();
				sysRole.setId(r.getStr("id"));
				sysRole.setRoleNameCn(r.getStr("roleNameCn"));
				sysRole.setRoleNameEn(r.getStr("roleNameEn"));
				
				list.add(sysRole);
			}
		}
		return list;
	}
}
