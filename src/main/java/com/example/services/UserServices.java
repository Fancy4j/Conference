package com.example.services;

import com.example.api.CommonResult;
import com.example.pojo.User;
import com.example.pojo.Userinfo;

import java.util.Date;
import java.util.List;

public interface UserServices {
    //查询
    public List<User> queryAll();
    //添加数据
    public int add(User userLogin);
    //根据用户名查询数据
    public User queryByName(String username);

    //根据id查询姓名
    public User queryByID(int id);


    public CommonResult updateUserPassword(Integer userId,String password);

    CommonResult addUser(Userinfo userInfo);

    CommonResult findUser(String userName, String userPwd);

    CommonResult updateUserStatus(String emial, Date appointTime, Integer meetingId);

    CommonResult getUserInfo(Integer userId);

    CommonResult updateUserInfo(Userinfo userInfo);
}
