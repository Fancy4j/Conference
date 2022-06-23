package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.ContributorMeeting;
import com.example.pojo.MeetingArticle;
import com.example.pojo.ReviewerReplay;
import com.example.pojo.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface ReviewerReplayMapper extends BaseMapper<ReviewerReplay> {

    ReviewerReplay getArticle(Integer article);

    int dispatchArticle(@Param("mapList") ArrayList<HashMap<String, Integer>> mapList);

}
