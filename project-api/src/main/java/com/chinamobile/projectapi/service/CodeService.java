package com.chinamobile.projectapi.service;

import com.chinamobile.projectapi.VO.ResultVo;
import com.chinamobile.projectapi.VO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @Description:
 * @Author: Winston Yang
 * @Date: Create in 14:04 2018/7/6
 * @Modified by:
 */
@Service
public class CodeService {
    @Autowired
    RestTemplate restTemplate;

//    public void getCode() {
//         restTemplate.getForObject("http://PROJECT-USER/getCode",String.class);
//       // restTemplate.get
//    }

    public ResultVo login(String  tel,String password, String code, String uuid){
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("tel",tel);
        requestEntity.add("password",password);
        requestEntity.add("code",code);
        requestEntity.add("uuid",uuid);
        ResponseEntity<ResultVo> responseEntity=restTemplate.postForEntity("http://PROJECT-USER/user/login",requestEntity,ResultVo.class);
        return responseEntity.getBody();
    }
}
