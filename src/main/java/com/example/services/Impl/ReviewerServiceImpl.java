package com.example.services.Impl;

import com.example.mapper.ReviewerReplayMapper;
import com.example.pojo.ReviewerReplay;
import com.example.services.ReviewerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewerServiceImpl implements ReviewerService {

    @Autowired
    ReviewerReplayMapper rrm;

    @Override
    public List<ReviewerReplay> getReviewerInfo(Integer reviewId){
        List<ReviewerReplay> result = null ;
        result =  rrm.getArticleInfo(reviewId);
        return result;
    }

    @Override
    public void insertReviewerInfo(ReviewerReplay reviewerReplay) {
        rrm.updateReviewerReplay(reviewerReplay);
        rrm.updateArticleStatus(reviewerReplay);
    }
}
