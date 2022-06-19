package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "主持人会议关系表")
public class HolderMeeting {

    @ApiModelProperty("id")
    @TableField("id")
    Integer id;

    @ApiModelProperty("主持人id")
    @TableField("user_id")
    Integer userId;

    @ApiModelProperty("会议id")
    @TableField("meeting_id")
    Integer meetingId;

}
