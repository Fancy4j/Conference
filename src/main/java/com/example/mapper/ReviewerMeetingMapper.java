package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.ReviewerMeeting;
import com.example.pojo.Userinfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewerMeetingMapper extends BaseMapper<ReviewerMeeting> {

    List<Userinfo> reviewerAllocatedByMeetingId(Integer meetingId);


    List<Integer> reviewerIdByMeetingId(Integer meetingId);

    List<Integer> getReviewerIds(Integer meetingId);

}
