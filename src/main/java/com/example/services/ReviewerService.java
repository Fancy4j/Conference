package com.example.services;

import com.example.api.CommonResult;

public interface ReviewerService {

    CommonResult reviewerAllocated(Integer meetingId);

    CommonResult reviewerAvailable(Integer meetingId);

    CommonResult dispatchArticle(Integer meetingId);
}
