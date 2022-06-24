package com.example.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.CommonResult;
import com.example.mapper.*;
import com.example.pojo.*;
import com.example.services.ContributorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    ContributorMapper contributorMapper;

    @Autowired
    ReviewerReplayMapper reviewerReplayMapper;

    @Autowired
    UserArticleMapper userArticleMapper;

    @Autowired
    MeetingArticleMapper meetingArticleMapper;

    @Autowired
    ReviewerMeetingMapper reviewerMeetingMapper;

    @Override
    public CommonResult addContribution(UserArticle userArticle, Integer meetingId) {
//        int insert = contributorMapper.addContribution(userArticle);
        Integer id = userArticleMapper.selectMaxArticleId();
        Integer ArticleId = id + 1;
        userArticle.setArticleId(ArticleId);
        int insert = userArticleMapper.insert(userArticle);

        ContributorMeeting contributorMeeting = new ContributorMeeting();
        contributorMeeting.setMeetingId(meetingId);
        contributorMeeting.setUserId(userArticle.getUserId());
        int insert2 = contributorMapper.insert(contributorMeeting);

        MeetingArticle meetingArticle = new MeetingArticle();
        meetingArticle.setArticleId(ArticleId);
        meetingArticle.setMeetingId(meetingId);
        int insert3 = meetingArticleMapper.insert(meetingArticle);

        if(insert > 0 && insert2 >0 && insert3 > 0){
            return CommonResult.success("投稿成功");
        }
        return CommonResult.failed("投稿失败");
    }

    /**
     * 查看某一个文章的稿件状态和评语
     * @param articleId
     * @return
     */
    @Override
    public CommonResult checkArticle(Integer articleId) {
        ReviewerReplay reviewerReplay = reviewerReplayMapper.getArticle(articleId);
        if( reviewerReplay == null){
            return CommonResult.failed("无法查看稿件状态");
        }
        return CommonResult.success(reviewerReplay,"查询稿件状态成功");
    }

    @Override
    public CommonResult queryArticles(Integer userId,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserArticle> userArticleList = userArticleMapper.selectArticleInfo(userId);
        PageInfo<UserArticle> pageInfo = new PageInfo<UserArticle>(userArticleList);
        return CommonResult.success(pageInfo,"查询成功");
    }

    @Override
    public CommonResult delContrubution(Integer articleId) {
        UserArticle userArticle = userArticleMapper.selectOne(new QueryWrapper<UserArticle>().eq("article_id", articleId));
        userArticle.setStatus(10);
        int article_id = userArticleMapper.update(userArticle,new QueryWrapper<UserArticle>().eq("article_id", articleId));
        if(article_id > 0){
            return CommonResult.success("取消投稿成功");
        }
        return CommonResult.failed("取消投稿失败");
    }

    @Override
    public CommonResult queryArticlesByName(Integer userId, String articleName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserArticle> userArticleList = userArticleMapper.selectArticleInfo2(userId,articleName);
        PageInfo<UserArticle> pageInfo = new PageInfo<UserArticle>(userArticleList);
        return CommonResult.success(pageInfo,"查询成功");
    }

    @Override
    public CommonResult getStatusTime(Integer articleId) {
        UserArticle userArticle = userArticleMapper.selectOne(new QueryWrapper<UserArticle>().eq("article_id", articleId));
        HashMap<String, Date> map = new HashMap<>();
        //投稿时间
        Date ctime = userArticle.getCtime();
        map.put("ctime",ctime);
        //稿件状态
        Integer status = userArticle.getStatus();
        if(status != 1 && status != 10){
            MeetingArticle meetingArticle = meetingArticleMapper.selectOne(new QueryWrapper<MeetingArticle>().eq("article_id", articleId));
            if(meetingArticle == null || meetingArticle.getMeetingId() == null){
                return CommonResult.failed("数据库数据异常,请工作人员维护");
            }
            ReviewerMeeting reviewerMeeting = reviewerMeetingMapper.selectOne(new QueryWrapper<ReviewerMeeting>().eq("meeting_id", meetingArticle.getMeetingId()));
            Date appointTime = reviewerMeeting.getAppointTime();
            map.put("appointTime",appointTime);
            if(status != 2) {
                ReviewerReplay reviewerReplay = reviewerReplayMapper.selectOne(new QueryWrapper<ReviewerReplay>().eq("article_id", articleId));
                if(reviewerReplay == null || reviewerReplay.getReplayTime() == null){
                    return CommonResult.failed("数据库数据异常，请工作人员维护呀");
                }
                Date replayTime = reviewerReplay.getReplayTime();
                map.put("replayTime",replayTime);
            }
        }
        return CommonResult.success(map,"查询成功");
    }
}
