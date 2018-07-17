package com.chinamobile.projectapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
public class ProjectApiApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjectApiApplication.class, args);
    }
//	@Bean
//	@LoadBalanced
//	RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
}
