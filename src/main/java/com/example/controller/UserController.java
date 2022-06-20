package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.User;
import com.example.pojo.Userinfo;
import com.example.services.UserServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/")
@Api(value = "系统用户接口", description = "用户注册、登录、修改个人信息")
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "lbf")
    public CommonResult userLogin(@RequestParam("userName") String userName, @RequestParam("userPwd") String userPwd) {
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd)){
            return CommonResult.validateFailed("用户名或密码为空");
        }
        try {
            return userServices.findUser(userName,userPwd);
        }catch (Exception e){
            e.printStackTrace();
            log.error("login接口异常，入参1为{}，入参2为{}",userName,userPwd);
            return CommonResult.failed("登录异常");
        }
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册(不需要userId,它是自增的)", notes = "lbf")
    public CommonResult userRegister(@RequestBody Userinfo userInfo){
        if(userInfo == null){
            return CommonResult.failed("注册信息不能为空");
        }
        try{
            return userServices.addUser(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("注册接口异常，入参为：{}",userInfo);
            return CommonResult.failed("注册失败");
        }
    }

    @PostMapping(value = "/userUpdatePassword")
    @ApiOperation(value = "修改用户密码", notes = "lbf")
    public CommonResult userUpdatePassword(@RequestParam("userId") Integer userId,@RequestParam("newPwd") String newPwd) {
        if(StringUtils.isEmpty(newPwd) || userId == null){
            return CommonResult.validateFailed("用户新密码为空");
        }
        try{
            return userServices.updateUserPassword(userId,newPwd);
        }catch (Exception e){
            e.printStackTrace();
            log.error("userUpdatePassword接口异常，入参1为{}，入参2为{}",userId,newPwd);
            return CommonResult.failed("用户修改密码异常");
        }
    }


}
