package com.example.services;

import com.example.api.CommonResult;
import com.example.pojo.Meetinginfo;

public interface HostService {
    /**
     * 主办会议
     * @param hostId
     * @param meetingInfo
     * @return
     */
    CommonResult addMeeting(Integer hostId, Meetinginfo meetingInfo);

    CommonResult queryMeetingList(Integer hostId,Integer pageNum,Integer pageSize);

    CommonResult updateMeeting(Meetinginfo meetingInfo);
}
