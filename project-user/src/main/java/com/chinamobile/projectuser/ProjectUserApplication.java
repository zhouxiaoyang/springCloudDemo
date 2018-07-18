package com.chinamobile.projectuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@MapperScan("com.chinamobile.projectuser.dao")
@SpringBootApplication

public class ProjectUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectUserApplication.class, args);
	}
}
