package com.example.controller;

import com.example.api.CommonResult;
import com.example.pojo.UserArticle;
import com.example.services.ContributorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.scene.chart.ValueAxis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/contributor")
@Api(value = "投稿人接口", description = "投稿人 投稿、查看稿件状态和稿件回复的功能")
public class ContributorController {

    @Autowired
    ContributorService contributorService;

    @PostMapping(value = "/addContribution")
    @ApiOperation(value = "投稿", notes = "lbf")
    public CommonResult addContribution(@RequestBody UserArticle userArticle,
                                     @RequestParam(value = "meetingId") Integer meetingId){
        if(meetingId == null || userArticle == null){
            return CommonResult.failed("参数不足");
        }
        try {
            return contributorService.addContribution(userArticle, meetingId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("addContribution接口异常，入参1：{},入参2：{}，异常信息{}",userArticle,meetingId,e.getMessage());
            return CommonResult.failed("投稿失败");
        }
    }

    @GetMapping("/checkArticle")
    @ApiOperation(value = "查看文章状态，已经审稿人回复")
    public CommonResult checkArticle(@RequestParam("articleId")Integer articleId){
        if (articleId == null) {
            return CommonResult.failed("参数不足");
        }
        try {
            return contributorService.checkArticle(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("checkArticle接口异常，入参为{}",articleId);
            return CommonResult.failed("无法查看文章状态");
        }
    }

    @GetMapping("/queryArticles")
    @ApiOperation(value = "查看所有文章状态")
    public CommonResult queryArticles(){
        try {
            return contributorService.queryArticles();
        }catch (Exception e){
            e.printStackTrace();
            log.error("queryArticles接口异常");
            return CommonResult.failed("无法查看投稿文章列表");
        }
    }

    @GetMapping("/delContrubution")
    @ApiOperation(value = "取消投稿")
    public CommonResult delContrubution(@RequestParam("articleId")Integer articleId){
        try {
            return contributorService.delContrubution(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("delContrubution接口异常,入参为{}",articleId);
            return CommonResult.failed("无法取消投稿");
        }
    }
}
