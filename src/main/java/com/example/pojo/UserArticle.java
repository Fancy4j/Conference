package com.example.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "投稿人 文章关系表")
public class UserArticle {

    @ApiModelProperty("文章id")
    @TableField("article_id")
    Integer articleId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    Integer userId;

    @ApiModelProperty("文章标题")
    @TableField("title")
    String title;

    @ApiModelProperty("文章内容")
    @TableField("content")
    String content;

    @ApiModelProperty("文章链接")
    @TableField("article_ref")
    String articleRef;

    @ApiModelProperty("文章关键字")
    @TableField("keywords")
    String keywords;

    @ApiModelProperty("投稿时间")
    @TableField("ctime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date ctime;

    @ApiModelProperty("文章审核状态")
    @TableField("status")
    Integer status;

    @ApiModelProperty("文章作者")
    @TableField("authors")
    String authors;

    @ApiModelProperty("会议id")
    @TableField(exist = false)
    Integer meetingId;

    @ApiModelProperty("主题")
    @TableField(exist = false)
    String theme;

    @ApiModelProperty("会议名称")
    @TableField(exist = false)
    String name;

    @ApiModelProperty("会议缩写")
    @TableField(exist = false)
    String abbreviation;

    @ApiModelProperty("开始时间")
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date starttime;

    @ApiModelProperty("结束时间")
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date endtime;

    @ApiModelProperty("地址")
    @TableField(exist = false)
    String location;

    @ApiModelProperty("会议网址链接")
    @TableField(exist = false)
    String ref;

    @ApiModelProperty("会议详情")
    @TableField(exist = false)
    String detail;

    @ApiModelProperty("会议状态")
    @TableField(exist = false)
    String conferenceStatus;

}
