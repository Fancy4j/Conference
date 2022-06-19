package com.example.services.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.api.CommonResult;
import com.example.mapper.ContributorMapper;
import com.example.mapper.ReviewerReplayMapper;
import com.example.pojo.ContributorMeeting;
import com.example.pojo.MeetingArticle;
import com.example.pojo.ReviewerReplay;
import com.example.pojo.UserArticle;
import com.example.services.ContributorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ContributorServiceImpl implements ContributorService {

    @Autowired
    ContributorMapper contributorMapper;

    @Autowired
    ReviewerReplayMapper reviewerReplayMapper;

    @Override
    public CommonResult addContribution(UserArticle userArticle, Integer meetingId) {
        int insert = contributorMapper.addContribution(userArticle);
        ContributorMeeting contributorMeeting = new ContributorMeeting();
        contributorMeeting.setMeetingId(meetingId);
        contributorMeeting.setUserId(userArticle.getUserId());
        int insert2 = contributorMapper.addMeetingContributor(contributorMeeting);
        MeetingArticle meetingArticle = new MeetingArticle();
        meetingArticle.setArticleId(userArticle.getArticleId());
        meetingArticle.setMeetingId(meetingId);
        int insert3 = contributorMapper.addMeetingArticle(meetingArticle);
        if(insert > 0 && insert2 >0 && insert3 > 0){
            return CommonResult.success("投稿成功");
        }
        return CommonResult.failed("投稿失败");
    }

    /**
     * 查看某一个文章的稿件状态和评语
     * @param article
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
    public CommonResult queryArticles() {
        List<UserArticle> userArticleList = contributorMapper.selectList(new QueryWrapper<>());
        return CommonResult.success(userArticleList,"查询成功");
    }

    @Override
    public CommonResult delContrubution(Integer articleId) {
        UserArticle userArticle = contributorMapper.selectOne(new QueryWrapper<UserArticle>().eq("article_id", articleId));
        userArticle.setStatus(4);
        int article_id = contributorMapper.update(userArticle,new QueryWrapper<UserArticle>().eq("article_id", articleId));
        if(article_id > 0){
            return CommonResult.success("取消投稿成功");
        }
        return CommonResult.failed("取消投稿失败");
    }
}
