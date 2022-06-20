package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.Meetinginfo;
import com.example.services.MeetingInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meeting")
@Api(value = "会议操作的接口", description = "提供会议信息相关信息查询、修改的功能")
public class MeetingInfoController {
    @Autowired
    MeetingInfoService meetingInfoService;

    @GetMapping(value = "/getMeetingList")
    @ApiOperation(value = "获取所有可以投的会议列表",notes = "lbf")
    public CommonResult meetingAllQuery(@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        if(pageNum == null || pageSize == null){
            return CommonResult.success(null,"分页参数获取失败！");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Meetinginfo> meetingInfos = meetingInfoService.getMeetingList();
        PageInfo<Meetinginfo> pageInfo = new PageInfo<Meetinginfo>(meetingInfos);
        return CommonResult.success(pageInfo,"查询成功！");

    }

}
