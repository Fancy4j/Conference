package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.MeetingArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MeetingArticleMapper extends BaseMapper<MeetingArticle> {

    List<Integer> getArticleIds(Integer meetingId);

}
