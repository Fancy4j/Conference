package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContributorMapper extends BaseMapper<UserArticle> {

    int addContribution(UserArticle userArticle);

    int addMeetingArticle(MeetingArticle meetingArticle);

    int addMeetingContributor(ContributorMeeting contributorMeeting);

    List<UserArticle> getContriubutionList();

}
