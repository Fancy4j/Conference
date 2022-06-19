package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.User;
import com.example.services.Impl.UserServicesImpl;
import com.example.services.UserServices;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/*
@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
    如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 */
@RestController
@RequestMapping("/")
@Api(value = "会议操作的接口", description = "提供会议信息相关信息查询、修改的功能")
public class UserController {

    @Autowired
    UserServices userServices;
    //用户登录
    @RequestMapping(value = "/Login",produces = "application/json;charset=utf-8",  method = RequestMethod.POST)
    public CommonResult userLogin(String userName, String userPwd) throws UnsupportedEncodingException {
        //先查询该用户名是否存在
        User user1= userServices.queryByName(userName);
        if(user1 != null){//  如果查询的用户不为空
            userPwd = java.net.URLDecoder.decode(userPwd,"UTF-8");
            if(user1.getPassword().equals(userPwd)){
                return CommonResult.success(user1,"登录成功");
            }else {
                return CommonResult.validateFailed("密码错误");
            }
        }
        // 如果查询的用户为空
        return  CommonResult.validateFailed("用户名不存在");
    }
    //用户注册
    @RequestMapping(value = "/Register",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public CommonResult userRegister(String userName, String userPwd, String realName) throws UnsupportedEncodingException {
        User user1 = userServices.queryByName(userName);
        if(user1!=null){
            return CommonResult.validateFailed("用户名已存在！");
        }
        String regex = "^[a-z0-9A-Z]+$";
        if(!userName.matches(regex)){
            return CommonResult.validateFailed("账号格式不对，账号只能由数字与字母组成！");
        }
        userPwd = java.net.URLDecoder.decode(userPwd,"UTF-8");
        User user = new User();
        user.setUsername(userName);
        user.setPassword(userPwd);
        //将账号密码加入到数据库中
        int add = userServices.add(user);
        user1 = userServices.queryByName(user.getUsername());
        return CommonResult.success(user1,"注册成功，请登录！");
    }

    //查询所有普通用户
    @RequestMapping(value = "/userQuery",produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public CommonResult userQuery(Integer pageNum){
        PageHelper.startPage(pageNum, 5);
        List<User> users = userServices.queryAll();
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        return CommonResult.success(pageInfo,"查询成功！");
    }
    //改变普通用户状态
    @RequestMapping(value = "/userStatusUpdate",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    public CommonResult userStatusUpdate(User user) {
        User user1 = userServices.queryByName(user.getUsername());
        //更新用户状态
        userServices.updateUserStatus(user1);
        return CommonResult.success(user1, "禁用成功！");
    }

    //重置普通用户密码
    @RequestMapping(value = "/userResetPassword",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    public CommonResult userResetPassword(User user) {
        //更新用户密码
        User user1 = (User) userUpdatePassword(user, "000000").getData();
        return CommonResult.success(user1, "重置密码成功！");
    }

    //修改密码
    @RequestMapping(value = "/userUpdatePassword",produces = "application/json;charset=utf-8",method = RequestMethod.POST)
    public CommonResult userUpdatePassword(User user, String newPwd) {
        User user1 = userServices.queryByName(user.getUsername());
        if(newPwd.equals(userServices.queryByName(user.getUsername()).getPassword())){
            return CommonResult.validateFailed("新密码不能与原密码相同！");
        }
        user1.setPassword(newPwd);
        //更新用户密码
        userServices.updateUserPassword(user1);
        return CommonResult.success(user1, "修改密码成功！");
    }
}
