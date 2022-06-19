package com.example.mapper;

import com.example.pojo.ContributorMeeting;
import com.example.pojo.MeetingArticle;
import com.example.pojo.ReviewerReplay;
import com.example.pojo.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewerReplayMapper {

    ReviewerReplay getArticle(Integer article);
}
