package com.example.services;

import com.example.pojo.ReviewerReplay;

import java.util.List;

public interface ReviewerService {

    public List<ReviewerReplay> getReviewerInfo(Integer reviewId);

    public void insertReviewerInfo(ReviewerReplay reviewerReplay) ;
}
