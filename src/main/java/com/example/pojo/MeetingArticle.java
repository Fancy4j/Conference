package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "文章会议关系表")
public class MeetingArticle {

    @ApiModelProperty("id")
    @TableField("id")
    Integer id;

    @ApiModelProperty("会议id")
    @TableField("meeting_id")
    Integer meetingId;

    @ApiModelProperty("文章id")
    @TableField("article_id")
    Integer articleId;

}
