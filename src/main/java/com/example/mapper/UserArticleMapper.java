package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.pojo.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserArticleMapper extends BaseMapper<UserArticle> {
    Integer selectMaxArticleId();

    List<UserArticle> selectArticleInfo(Integer userId);

    List<UserArticle> selectArticleInfo2(Integer userId, String articleName);
}
