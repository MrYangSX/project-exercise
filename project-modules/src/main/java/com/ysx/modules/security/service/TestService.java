package com.ysx.modules.security.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysx.modules.security.domain.Test;

/**
 * <p>
 * 测试 服务类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-25
 */
public interface TestService extends IService<Test> {

	public List<Test> findMpTests();
}
