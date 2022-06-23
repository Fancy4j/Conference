package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Repository
public interface UserInfoMapper extends BaseMapper<Userinfo> {

    List<Userinfo> reviewerAvailable(@Param("reviewerIdList")List<Integer> reviewerIdList, @Param("meetingId") Integer meetingId);

    int updateStatus2(@Param("idList") ArrayList<Integer> idList);

    int delStatus2(@Param("idList")ArrayList<Integer> idList);
}
