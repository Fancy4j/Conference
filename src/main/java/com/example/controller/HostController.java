package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.Meetinginfo;
import com.example.services.HostService;
import com.example.services.MeetingInfoService;
import com.example.services.ReviewerService;
import com.example.services.UserServices;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/host")
@Api(value = "主持人接口", description = "主持人 指派审稿人、主办会议、查询已主办会议列表、修改会议信息 的功能")
public class HostController {

    @Autowired
    UserServices userServices;

    @Autowired
    HostService hostService;

    @Autowired
    ReviewerService reviewerService;


    @PostMapping(value = "/userStatusUpdate")
    @ApiOperation(value = "指派审稿人（修改用户角色）", notes = "lbf")
    public CommonResult userStatusUpdate(@RequestParam("userIds") String userIds,
                                         @RequestParam(value = "appointTime",required = false) String appointTime,
                                         @RequestParam("meetingId")Integer meetingId) {
        if(StringUtils.isEmpty(userIds)){
            return CommonResult.validateFailed("userId为空");
        }
        if(meetingId == null){
            return CommonResult.validateFailed("会议号为空");
        }
        try{
            Date date = null;
            if(appointTime != null) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = simpleDateFormat.parse(appointTime);
            }
            return userServices.updateUserStatus(userIds,date,meetingId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("userStatusUpdate接口异常，入参1为{},入参2为{}",userIds,meetingId);
            return CommonResult.failed("增加审稿人异常");
        }
    }

    @GetMapping(value = "/reviewerAllocated")
    @ApiOperation(value = "根据会议id,查询已经指派的审稿人", notes = "lbf")
    public CommonResult reviewerAllocated(@RequestParam("meetingId") Integer meetingId) {
        if(meetingId == null){
            return CommonResult.validateFailed("会议号为空");
        }
        try{
            return reviewerService.reviewerAllocated(meetingId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("reviewerAllocated接口异常，入参1为{}",meetingId);
            return CommonResult.failed("查询已指派的审稿人异常");
        }
    }

    @GetMapping(value = "/reviewerAvailable")
    @ApiOperation(value = "根据会议id,查询没有被指派的人", notes = "lbf")
    public CommonResult reviewerAvailable(@RequestParam("meetingId") Integer meetingId) {
        if(meetingId == null){
            return CommonResult.validateFailed("会议号为空");
        }
        try{
            return reviewerService.reviewerAvailable(meetingId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("reviewerAvailable接口异常，入参1为{}",meetingId);
            return CommonResult.failed("查询未指派的人异常");
        }
    }



    @PostMapping(value = "/addMeeting")
    @ApiOperation(value = "主办会议（增加会议）", notes = "lbf")
    public CommonResult addMeeting(@RequestParam("hostId")Integer hostId, @RequestBody Meetinginfo meetingInfo) {
        if(meetingInfo == null){
            return CommonResult.validateFailed("会议信息为空");
        }
        if(hostId == null){
            return CommonResult.validateFailed("主持人为空");
        }
        try{
            return hostService.addMeeting(hostId,meetingInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("addMeeting接口异常，入参1为{},入参2为{}",hostId,meetingInfo);
            return CommonResult.failed("增加会议异常");
        }
    }

    @GetMapping(value = "/queryMeetingList")
    @ApiOperation(value = "查询已经主办的会议列表", notes = "lbf")
    public CommonResult queryMeetingList(@RequestParam("hostId")Integer hostId,
                                         @RequestParam("pageNum")Integer pageNum,
                                         @RequestParam("pageSize")Integer pageSize) {
        if(hostId == null){
            return CommonResult.validateFailed("主持人id不能为空");
        }
        if(pageNum == null || pageSize == null){
            return CommonResult.success(null,"分页参数获取失败！");
        }
        try{
            return hostService.queryMeetingList(hostId,pageNum,pageSize);
        }catch (Exception e){
            e.printStackTrace();
            log.error("queryMeetingList接口异常，入参1为{},入参2{}，入参3{}",hostId,pageNum,pageSize);
            return CommonResult.failed("查询会议列表异常");
        }
    }

    @PostMapping(value = "/updateMeeting")
    @ApiOperation(value = "修改增加会议信息", notes = "lbf")
    public CommonResult updateMeeting(@RequestBody Meetinginfo meetingInfo) {
        if(meetingInfo == null){
            return CommonResult.validateFailed("会议信息为空");
        }
        if(meetingInfo.getMeetingId() == null){
            return CommonResult.validateFailed("会议id为空");
        }
        try{
            return hostService.updateMeeting(meetingInfo);
        }catch (Exception e){
            e.printStackTrace();
            log.error("updateMeeting接口异常，入参1为{}",meetingInfo);
            return CommonResult.failed("修改会议异常");
        }
    }

}
