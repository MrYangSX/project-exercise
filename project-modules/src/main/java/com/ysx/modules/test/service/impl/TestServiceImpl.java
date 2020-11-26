package com.ysx.modules.test.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysx.modules.test.domain.Test;
import com.ysx.modules.test.mapper.TestMapper;
import com.ysx.modules.test.service.TestService;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试 服务实现类
 * </p>
 *
 * @author yangShiXiong
 * @since 2020-11-25
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

	@Override
	public List<Test> findMpTests() {
		// TODO Auto-generated method stub
		return this.baseMapper.selectList(null);
	}

}
