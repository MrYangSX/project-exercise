package com.ysx.modules.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.ysx.modules.mapper.SysUserRoleMapper;
import com.ysx.modules.service.SysUserRoleService;
import com.ysx.utils.lang.ObjectUtil;
import com.ysx.utils.lang.StringUtil;

/**
 * 
 * @author yangShiXiong
 * @Data 2020年11月27日
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService{

	@Autowired
	SysUserRoleMapper sysUserRoleMapper;
	
	@Override
	public String getUserIdByRId(String roleId) {
		// TODO Auto-generated method stub
		if (!ObjectUtil.isEmpty(roleId)) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("SELECT user_id AS userId FROM sys_user_role WHERE role_id = ?");
			Record r = Db.findFirst(sBuffer.toString(), roleId);
			if (!ObjectUtil.isEmpty(r)) {
				return r.getStr("userId	");
			}
		}
		return null;
	}

	@Override
	public List<String> getRoleIdByUId(String userId) {
		List<String> list = new ArrayList<String>();
		// TODO Auto-generated method stub
		if (!ObjectUtil.isEmpty(userId)) {
			StringBuffer sBuffer = new StringBuffer();
			sBuffer.append("SELECT role_id AS roleId FROM sys_user_role WHERE user_id = ?");
			List<Record> records = Db.find(sBuffer.toString(), userId);
			if (!ObjectUtil.isEmpty(records)) {
				for (Record r : records) {
					list.add(r.getStr("roleId"));
				}
			}
		}
		return list;
	}

	@Override
	public int save(String userId, List<String> roleIds) {
		// TODO Auto-generated method stub
		if (StringUtil.isNotBlank(userId)) {
			StringBuffer sBuffer = new StringBuffer();
			//delete
			sBuffer.append("DELETE FROM sys_user_role WHERE user_id = ?");
			Db.delete(sBuffer.toString(), userId);
			
			if (ObjectUtil.isEmpty(roleIds)) {
				return 1;
			}
			
			String valueString = null;
			for (int i = 0; i < roleIds.size(); i++) {
				StringBuffer sb = new StringBuffer();
				sb.append("'"+ roleIds.get(i) +"'");
				if (i != (roleIds.size()-1)) {
					sb.append(",");
				}
				valueString += sb.toString();
			}
			sBuffer.delete(0, sBuffer.length()-1);
			//insert
			sBuffer.append("INSERT INTO sys_user_role (role_id) VALUE ("+ valueString +")");
			sBuffer.append("WHERE user_id = ?");
			
		}
		return 0;
	}
	
}
