package com.ysx.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = {"com.ysx.*","com.ysx.utils","com.ysx.security", "com.ysx.modules","com.ysx.web"})
@MapperScan(basePackages = {"com.ysx.modules.*.mapper", "com.ysx.modules.mapper"})
public class ProjectWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectWebApplication.class, args);
	}

//	开启加密方式
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
