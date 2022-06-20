package com.example.services;

import com.example.pojo.Meetinginfo;

import java.util.List;

public interface MeetingInfoService {
    //查询当前可以投稿的会议列表
    List<Meetinginfo> getMeetingList();

}
