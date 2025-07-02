package com.mes.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mes.system.mapper")
public class SystemApplication {

	public static void main(String[] args) {
		// 设置生产环境配置
		if (System.getenv("RAILWAY_ENVIRONMENT") != null) {
			System.setProperty("spring.profiles.active", "prod");
		}
		SpringApplication.run(SystemApplication.class, args);
	}

}
