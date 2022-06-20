package com.example.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.CommonResult;
import com.example.mapper.ReviewerMeetingMapper;
import com.example.mapper.UserInfoMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.ReviewerMeeting;
import com.example.pojo.User;
import com.example.pojo.Userinfo;
import com.example.services.UserServices;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    ReviewerMeetingMapper reviewerMeetingMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public int add(User user) {
        return userMapper.add(user);
    }

    @Override
    public User queryByName(String username) {
        return userMapper.queryByName(username);
    }

    @Override
    public User queryByID(int id) {
        return userMapper.queryByID(id);
    }

    /**
     * 根据userId修改用户密码
     * @param userId
     * @param password
     * @return
     */
    @Override
    public CommonResult updateUserPassword(Integer userId,String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userId));
        if(password.equals(user.getPassword())){
            return CommonResult.failed("新密码不能与旧密码一致");
        }
        user.setPassword(password);
        int update = userMapper.updateUserPassword(user);
        if(update > 0){
            return CommonResult.success(user,"修改密码成功");
        }
        return CommonResult.failed("修改密码失败");
    }

    /**
     * 增加系统用户
     * @param userInfo
     * @return
     */
    @Override
    public CommonResult addUser(Userinfo userInfo) {
        String username = userInfo.getUsername();
        if(StringUtils.isEmpty(username)){
            return CommonResult.validateFailed("用户名不能为空");
        }
        if(StringUtils.isEmpty(userInfo.getEmail())){
            return CommonResult.validateFailed("邮箱不能为空");
        }
        if(StringUtils.isEmpty(userInfo.getName())){
            return CommonResult.validateFailed("真实姓名不能为空");
        }
        if(StringUtils.isEmpty(userInfo.getUserRole())){
            return CommonResult.validateFailed("用户身份不能为空");
        }
        User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        if(user1!=null){
            return CommonResult.validateFailed("用户名已存在！");
        }
        String regex = "^[a-z0-9A-Z]+$";
        if(!username.matches(regex)){
            return CommonResult.validateFailed("账号格式不对，账号只能由数字与字母组成！");
        }
        //将账号密码加入到数据库中
        User user = new User();
        user.setPassword(userInfo.getPassword());
        user.setUsername(username);
        user.setUserRole(userInfo.getUserRole());
        int insert = userMapper.insert(user);
        User user2 = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        userInfo.setUserId(user2.getUserId());
        int insert1 = userInfoMapper.insert(userInfo);
        if(insert > 0 && insert1 > 0){
            return CommonResult.success(userInfo,"注册成功，请登录");
        }
        return CommonResult.success("注册成功，请登录！");
    }

    /**
     * 根据用户名和用户密码来查询用户信息
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public CommonResult findUser(String userName, String userPwd) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", userName).eq("password", userPwd));
        if(user == null){
            return CommonResult.failed("用户名或者密码错误");
        }
        return CommonResult.success(user,"登录成功");
    }

    /**
     * 增加审稿人（修改用户角色）
     * @param email
     * @param userRole
     * @return
     */
    @Override
    public CommonResult updateUserStatus(String email, String userRole, Integer meetingId) {

        Userinfo userInfo = userInfoMapper.selectOne(new QueryWrapper<Userinfo>().eq("email",email));
        if(userInfo == null){
            return CommonResult.failed("不存在此用户id的用户");
        }
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_id", userInfo.getUserId()));
        if(user == null){
            return CommonResult.failed("不存在此用户id的用户");
        }
        user.setUserRole(userRole);
        userInfo.setUserRole(userRole);
        int update = userMapper.update(user,new QueryWrapper<User>().eq("user_id",userInfo.getUserId()));
        int update1 = userInfoMapper.update(userInfo,new QueryWrapper<Userinfo>().eq("user_id",userInfo.getUserId()));
        ReviewerMeeting reviewerMeeting = new ReviewerMeeting();
        reviewerMeeting.setMeetingId(meetingId);
        reviewerMeeting.setUserId(userInfo.getUserId());
        int insert = reviewerMeetingMapper.insert(reviewerMeeting);
        if(update > 0 && update1 > 0 && insert > 0){
            return CommonResult.success("增加审稿人成功");
        }
        return CommonResult.failed("增加审稿人失败");
    }
}