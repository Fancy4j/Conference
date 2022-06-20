package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.HolderMeeting;
import com.example.pojo.Meetinginfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HolderMeetingMapper extends BaseMapper<HolderMeeting> {

    List<Meetinginfo> selectMeetingByHostId(Integer hostId);
}
