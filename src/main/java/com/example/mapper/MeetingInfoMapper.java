package com.example.mapper;

import com.example.pojo.MeetingInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MeetingInfoMapper {

    //  查询当前可投的会议列表
    List<MeetingInfo> meetingAvailble();

}
