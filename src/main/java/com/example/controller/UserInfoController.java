package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.Userinfo;
import com.example.services.UserServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/userInfo")
@Api(value = "投稿人、主持人、审稿人 详细信息接口", description = "查询、修改详细的个人信息")
public class UserInfoController {

    @Autowired
    UserServices userServices;

    @GetMapping(value = "/getUserInfo")
    @ApiOperation(value = "查看用户详细信息", notes = "lbf")
    public CommonResult getUserInfo(@RequestParam("userId") Integer userId) {
        if(userId == null){
            return CommonResult.validateFailed("用户id为空");
        }
        try {
            return userServices.getUserInfo(userId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("getUserInfo接口异常，入参1为{}",userId);
            return CommonResult.failed("查看用户详细信息异常");
        }
    }

    @PostMapping(value = "/updateUserInfo")
    @ApiOperation(value = "修改用户详细信息", notes = "lbf")
    public CommonResult updateUserInfo(@RequestBody Userinfo userInfo){
        if(userInfo == null){
            return CommonResult.failed("修改的用户信息不能为空");
        }
        try{
            return userServices.updateUserInfo(userInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("updateUserInfo接口异常，入参为：{}",userInfo);
            return CommonResult.failed("修改用户详细信息失败");
        }
    }



}
