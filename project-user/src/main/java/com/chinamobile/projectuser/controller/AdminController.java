package com.chinamobile.projectuser.controller;

import com.chinamobile.projectuser.datavo.ResultVo;
import com.chinamobile.projectuser.form.User;
import com.chinamobile.projectuser.service.AdminService;
import com.chinamobile.projectuser.util.ResultVoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Description: 管理员
 * @Author: Winston Yang
 * @Date: Create in 14:55 2018/7/20
 * @Modified by:
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("getUserList")
    public ResultVo getUserList(@RequestParam(name = "currentPage",defaultValue = "1") Integer currentPage,
           @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize){

        Map<String,Object>map=adminService.getUserList(currentPage,pageSize);
        return ResultVoUtil.success(map);

    }

    @PostMapping("/addUser")
    public ResultVo addUser(@RequestBody User user){

        if (adminService.addUser(user)!=1){
            return ResultVoUtil.fail();
        }

        return ResultVoUtil.success();
    }
    @PostMapping("/updateUser")
    public ResultVo updateUser(@RequestBody User user){

        if (adminService.updateUser(user)!=1){
            return ResultVoUtil.fail();
        }

        return ResultVoUtil.success();
    }

    @PostMapping("/deleteUser")
    public ResultVo deleteUser(@RequestParam Integer id){

        if (adminService.deleteUser(id)!=1){
            return ResultVoUtil.fail();
        }

        return ResultVoUtil.success();
    }

}
