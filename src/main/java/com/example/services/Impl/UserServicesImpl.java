package com.example.services.Impl;



import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserMapper userMapper;

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

    @Override
    public int updateUserStatus(User user) {
        return userMapper.updateUserStatus(user);
    }

    @Override
    public int updateUserPassword(User user) {
        return userMapper.updateUserPassword(user);
    }
}