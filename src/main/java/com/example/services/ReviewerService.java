package com.example.services;

import com.example.pojo.ReviewerReplay;

import java.util.List;
import com.example.api.CommonResult;

public interface ReviewerService {

    public List<ReviewerReplay> getReviewerInfo(Integer reviewId);

    public void insertReviewerInfo(ReviewerReplay reviewerReplay) ;

    CommonResult reviewerAllocated(Integer meetingId);

    CommonResult reviewerAvailable(Integer meetingId);

    CommonResult dispatchArticle(Integer meetingId);
}
