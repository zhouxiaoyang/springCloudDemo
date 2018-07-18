package com.chinamobile.projectapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 17:29 2018/7/17
 * @Modified by:
 */
@FeignClient(value = "project-user")
public interface UserClient {

    @GetMapping("/project/getCode")
    void getCode();
}
