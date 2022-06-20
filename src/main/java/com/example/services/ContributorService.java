package com.example.services;

import com.example.api.CommonResult;
import com.example.pojo.UserArticle;

public interface ContributorService {
    CommonResult addContribution(UserArticle userArticle, Integer meetingId);

    CommonResult checkArticle(Integer articleId);

    CommonResult queryArticles(Integer userId,Integer pageNum,Integer pageSize);

    CommonResult delContrubution(Integer articleId);
}
