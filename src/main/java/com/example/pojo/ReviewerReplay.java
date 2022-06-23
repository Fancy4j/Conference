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
@ApiModel(value = "投稿人回复表")
public class ReviewerReplay {

    @ApiModelProperty("id")
    @TableField("id")
    Integer id;

    @ApiModelProperty("审稿人id")
    @TableField("reviewer_id")
    Integer reviewerId;

    @ApiModelProperty("审稿人名称")
    @TableField(exist = false)
    String reviewerName;

    @ApiModelProperty("文章id")
    @TableField("article_id")
    Integer articleId;



    @ApiModelProperty("会议id")
    @TableField(exist = false)
    Integer meetingId;

    @ApiModelProperty("会议名称")
    @TableField(exist = false)
    String meetingName;


    @ApiModelProperty("投稿时间")
    @TableField(exist = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date ctime;




    @ApiModelProperty("文章标题")
    @TableField(exist = false)
    String title;


    @ApiModelProperty("文章链接")
    @TableField(exist = false)
    String articleRef;






    @ApiModelProperty("文章审核状态")
    @TableField(exist = false)
    Integer status;

    @ApiModelProperty("回复内容")
    @TableField("replay")
    String replay;

    @ApiModelProperty("回复时间")
    @TableField("replay_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date replayTime;

}
