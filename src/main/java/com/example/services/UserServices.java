package com.example.services;

import com.example.pojo.User;

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

    //禁用用户
    public int updateUserStatus(User user);

    //重置用户密码
    public int updateUserPassword(User user);
}
