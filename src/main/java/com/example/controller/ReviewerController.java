package com.example.controller;


import com.example.api.CommonResult;
import com.example.pojo.Meetinginfo;
import com.example.pojo.ReviewerReplay;
import com.example.pojo.Userinfo;
import com.example.services.ReviewerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/reviewer")
@Api(value = "审稿人接口", description = "审稿人需要的相关功能")
public class ReviewerController {

    @Autowired
    ReviewerService rs;

    @GetMapping("/queryAllMessage")
    @ApiOperation(value = "获取与当前审稿人相关的所有文章信息",notes = "wj")
    public CommonResult queryAllMessage(@RequestParam("reviewerId") Integer reviewerId,@RequestParam("pageNum")Integer pageNum, @RequestParam("pageSize")Integer pageSize){
        if(pageNum == null || pageSize == null){
            return CommonResult.success(null,"分页参数获取失败！");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ReviewerReplay> results =  rs.getReviewerInfo(reviewerId);
        PageInfo<ReviewerReplay> pageInfo = new PageInfo<ReviewerReplay>(results);
        return CommonResult.success(pageInfo,"查询成功！");
    }
    @PostMapping("/addReviewerRecord")
    @ApiOperation(value = "提供审稿人ID,文章ID,评语（replay）和回复时间replay_time 插入reviewer_replay表中，供文章ID以及status 更新对应文章状态（user_article.status）",notes = "wj")
    public CommonResult addReviewerRecord(@RequestBody ReviewerReplay reviewerReplay) throws ParseException {

        try{
            rs.insertReviewerInfo(reviewerReplay);
            return CommonResult.success("成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("插入审稿人相关信息接口异常，入参为：{}",reviewerReplay);
            return CommonResult.failed("注册失败");
        }
    }
}
