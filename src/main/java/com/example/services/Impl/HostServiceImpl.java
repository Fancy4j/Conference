package com.example.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.CommonResult;
import com.example.mapper.HolderMeetingMapper;
import com.example.mapper.MeetingInfoMapper;
import com.example.pojo.HolderMeeting;
import com.example.pojo.Meetinginfo;
import com.example.services.HostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HostServiceImpl implements HostService {

    @Autowired
    HolderMeetingMapper holderMeetingMapper;

    @Autowired
    MeetingInfoMapper meetingInfoMapper;

    /**
     * 主办会议
     * @param hostId
     * @param meetingInfo
     * @return
     */
    @Override
    public CommonResult addMeeting(Integer hostId, Meetinginfo meetingInfo) {
        //参数校验
        if(StringUtils.isEmpty(meetingInfo.getName())){
            return CommonResult.validateFailed("会议名称不能为空");
        }
        if(StringUtils.isEmpty(meetingInfo.getTheme())){
            return CommonResult.validateFailed("会议主题不能为空");
        }
        if(StringUtils.isEmpty(meetingInfo.getLocation())){
            return CommonResult.validateFailed("会议主办地址不能为空");
        }
        if(StringUtils.isEmpty(meetingInfo.getRef())){
            return CommonResult.validateFailed("会议网址不能为空");
        }
        if(meetingInfo.getEndtime() == null){
            return CommonResult.validateFailed("会议结束时间不能为空");
        }
        if(meetingInfo.getStarttime() == null){
            return CommonResult.validateFailed("会议开始时间不能为空");
        }
        Integer id = meetingInfoMapper.selctMaxMeetingId();
        Integer meetingid = id +1;
        meetingInfo.setMeetingId(meetingid);
        int insert1 = meetingInfoMapper.insert(meetingInfo);
        HolderMeeting holderMeeting = new HolderMeeting();
        holderMeeting.setMeetingId(meetingid);
        holderMeeting.setUserId(hostId);
        int insert = holderMeetingMapper.insert(holderMeeting);
        if(insert > 0 && insert1 > 0){
            return CommonResult.success("添加会议成功");
        }
        return CommonResult.failed("添加会议失败");
    }

    /**
     * 查询该主持人 举办的会议
     * @param hostId
     * @return
     */
    @Override
    public CommonResult queryMeetingList(Integer hostId, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Meetinginfo> meetingList = holderMeetingMapper.selectMeetingByHostId(hostId);
        PageInfo<Meetinginfo> pageInfo = new PageInfo<Meetinginfo>(meetingList);
        return CommonResult.success(pageInfo,"查询成功");
    }

    @Override
    public CommonResult updateMeeting(Meetinginfo meetingInfo) {

        int i = meetingInfoMapper.update(meetingInfo,new QueryWrapper<Meetinginfo>().eq("meeting_id",meetingInfo.getMeetingId()));
        if(i > 0){
            return CommonResult.success("修改会议信息成功");
        }
        return CommonResult.failed("修改会议信息失败");
    }
}
