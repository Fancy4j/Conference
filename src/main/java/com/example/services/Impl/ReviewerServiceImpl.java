package com.example.services.Impl;

import com.example.api.CommonResult;
import com.example.mapper.MeetingArticleMapper;
import com.example.mapper.ReviewerMeetingMapper;
import com.example.mapper.ReviewerReplayMapper;
import com.example.mapper.UserInfoMapper;
import com.example.pojo.Userinfo;
import com.example.pojo.ReviewerReplay;
import com.example.services.ReviewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class ReviewerServiceImpl implements ReviewerService {

    @Autowired
    ReviewerMeetingMapper reviewerMeetingMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    MeetingArticleMapper meetingArticleMapper;

    @Autowired
    ReviewerReplayMapper reviewerReplayMapper;

    @Override
    public CommonResult reviewerAllocated(Integer meetingId) {
        List<Userinfo> reviewerList = reviewerMeetingMapper.reviewerAllocatedByMeetingId(meetingId);
        return CommonResult.success(reviewerList,"查询成功");
    }

    @Override
    public CommonResult reviewerAvailable(Integer meetingId) {
        List<Integer> reviewerIdList = reviewerMeetingMapper.reviewerIdByMeetingId(meetingId);
        List<Userinfo> reviewerList = userInfoMapper.reviewerAvailable(reviewerIdList);
        return CommonResult.success(reviewerList,"查询成功");
    }

    /**
     * 一键派稿
     * @param meetingId
     * @return
     */
    @Override
    public CommonResult dispatchArticle(Integer meetingId) {
        List<Integer> articleIds = meetingArticleMapper.getArticleIds(meetingId);
        List<Integer> reviewerIds = reviewerMeetingMapper.getReviewerIds(meetingId);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        //打乱文章id、审稿人id 顺序
        Collections.shuffle(articleIds);
        Collections.shuffle(reviewerIds);
        //审稿人的个数
        int num = reviewerIds.size();
        //每个审稿人至少要审的篇数，最后一个审稿人要审
        int averageNum = articleIds.size() / num;

        ArrayList<HashMap<String, Integer>> mapList = new ArrayList<>();
        for(int i = 0; i < num-1; i++){
            int count = i * averageNum;
            for(int j = 0; j < averageNum; j++){
                HashMap<String, Integer> hashMap = new HashMap<>();
                Integer articleId = articleIds.get(count + j);
                hashMap.put("reviewerId",reviewerIds.get(i));
                hashMap.put("articleId",articleId);
                mapList.add(hashMap);
            }
        }
        for(int i = averageNum * (num-1);i < articleIds.size();i++){
            HashMap<String, Integer> hashMap = new HashMap<>();
            Integer articleId = articleIds.get(i);
            hashMap.put("reviewerId",reviewerIds.get(num-1));
            hashMap.put("articleId",articleId);
            mapList.add(hashMap);
        }
        int insert = reviewerReplayMapper.dispatchArticle(mapList);
        if(insert > 0){
            return CommonResult.success("派稿成功");
        }

        return CommonResult.failed("派稿失败");
    }

    @Override
    public List<ReviewerReplay> getReviewerInfo(Integer reviewId){
        List<ReviewerReplay> result = null ;
        result =  reviewerReplayMapper.getArticleInfo(reviewId);
        return result;
    }

    @Override
    public void insertReviewerInfo(ReviewerReplay reviewerReplay) {
        reviewerReplayMapper.updateReviewerReplay(reviewerReplay);
        reviewerReplayMapper.updateArticleStatus(reviewerReplay);
    }
}
