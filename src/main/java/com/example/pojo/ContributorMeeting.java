package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "投稿人会议关系表")
public class ContributorMeeting {
    @ApiModelProperty("id")
    @TableField("id")
    Integer id;

    @ApiModelProperty("会议id")
    @TableField("meeting_id")
    Integer meetingId;

    @ApiModelProperty("投稿人id")
    @TableField("user_id")
    Integer userId;

}
