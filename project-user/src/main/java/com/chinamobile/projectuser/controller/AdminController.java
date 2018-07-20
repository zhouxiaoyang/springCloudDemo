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

    @GetMapping("getUserList/{page}/{pageSize}")
    public ResultVo getUserList(@PathVariable("page") Integer page,
                                @PathVariable("pageSize") Integer pageSize){
        Integer currentPage= page==null ? 1:page;
        pageSize= pageSize==null ? 10:pageSize;
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

    @PostMapping("/deleteUser/{id}")
    public ResultVo deleteUser(@PathVariable("id") Integer id){

        if (adminService.deleteUser(id)!=1){
            return ResultVoUtil.fail();
        }

        return ResultVoUtil.success();
    }

}
