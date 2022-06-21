package com.example.services.Impl;

import com.example.api.CommonResult;
import com.example.mapper.ReviewerMeetingMapper;
import com.example.mapper.UserInfoMapper;
import com.example.pojo.Userinfo;
import com.example.services.ReviewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewerServiceImpl implements ReviewerService {

    @Autowired
    ReviewerMeetingMapper reviewerMeetingMapper;

    @Autowired
    UserInfoMapper userInfoMapper;



    @Override
    public CommonResult reviewerAllocated(Integer meetingId) {
        List<Userinfo> reviewerList = reviewerMeetingMapper.reviewerAllocatedByMeetingId(meetingId);
        return CommonResult.success(reviewerList,"查询成功");
    }

    @Override
    public CommonResult reviewerAvailable(Integer meetingId) {
        List<Integer> reviewerIdList = reviewerMeetingMapper.reviewerIdByMeetingId(meetingId);
        List<Userinfo> reviewerList = userInfoMapper.reviewerAvailable(reviewerIdList);
        return CommonResult.success(reviewerList,"查询成功");
    }
}
