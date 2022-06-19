package com.example.mapper;

import com.example.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //查询所有普通用户
    public List<User> queryAll();
    //添加数据
    public int add(User user);
    //根据用户名查询数据
    public User queryByName(String userName);

    //根据id查询姓名
    public User queryByID(int id);

    //更新用户状态
    public int updateUserStatus(User user);

    //重置用户密码
    public int updateUserPassword(User user);

}
