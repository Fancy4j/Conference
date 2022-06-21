package com.example.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/reviewer")
@Api(value = "审稿人接口", description = "审稿人 查看待审文章、主办会议、查询已主办会议列表、修改会议信息 的功能")
public class ReviewerController {



}
