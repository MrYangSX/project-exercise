package com.ysx.modules.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色信息
 * 权限格式为ROLE_XXX，是Spring Security规定
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;
    //角色中文名称
    private String roleNameCn;
    //角色英文名称
    private String roleNameEn;
    
    private List<SysPermission> permissions;
}
