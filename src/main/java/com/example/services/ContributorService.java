package com.example.services;

import com.example.api.CommonResult;
import com.example.pojo.UserArticle;

public interface ContributorService {
    CommonResult addContribution(UserArticle userArticle, Integer meetingId);

    CommonResult checkArticle(Integer articleId);

    CommonResult queryArticles();

    CommonResult delContrubution(Integer articleId);
}
