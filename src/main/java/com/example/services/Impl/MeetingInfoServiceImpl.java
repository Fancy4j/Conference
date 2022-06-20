package com.example.services.Impl;

import com.example.mapper.MeetingInfoMapper;
import com.example.pojo.Meetinginfo;
import com.example.services.MeetingInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MeetingInfoServiceImpl implements MeetingInfoService {

    @Autowired
    MeetingInfoMapper meetingInfoMapper;

    @Override
    public List<Meetinginfo> getMeetingList() {
        return meetingInfoMapper.meetingAvailble();
    }
}
