package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.ReviewerReplay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Mapper
@Repository
public interface ReviewerReplayMapper extends BaseMapper<ReviewerReplay> {


    int dispatchArticle(@Param("mapList") ArrayList<HashMap<String, Integer>> mapList);

    ReviewerReplay getArticle(Integer articleId);

    List<ReviewerReplay> getArticleInfo(Integer reviewId);

    void insertReviewerReplay(ReviewerReplay reviewerReplay);

    void updateArticleStatus(ReviewerReplay reviewerReplay);

    void updateReviewerReplay(ReviewerReplay reviewerReplay);
}
