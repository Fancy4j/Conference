package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.ContributorMeeting;
import com.example.pojo.MeetingArticle;
import com.example.pojo.ReviewerReplay;
import com.example.pojo.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewerReplayMapper extends BaseMapper<ReviewerReplay> {

    ReviewerReplay getArticle(Integer articleId);

    List<ReviewerReplay> getArticleInfo(Integer reviewId);

    void insertReviewerReplay(ReviewerReplay reviewerReplay);

    void updateArticleStatus(ReviewerReplay reviewerReplay);

    void updateReviewerReplay(ReviewerReplay reviewerReplay);
}
