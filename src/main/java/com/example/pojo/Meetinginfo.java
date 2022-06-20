package com.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;


@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "会议信息表")
public class Meetinginfo {

    @ApiModelProperty("会议id")
    @TableField("meeting_id")
    Integer meetingId;

    @ApiModelProperty("主题")
    @TableField("theme")
    String theme;

    @ApiModelProperty("会议名称")
    @TableField("name")
    String name;

    @ApiModelProperty("会议缩写")
    @TableField("abbreviation")
    String abbreviation;

    @ApiModelProperty("开始时间")
    @TableField("starttime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date starttime;

    @ApiModelProperty("结束时间")
    @TableField("endtime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date endtime;

    @ApiModelProperty("地址")
    @TableField("location")
    String location;

    @ApiModelProperty("会议网址链接")
    @TableField("ref")
    String ref;

    @ApiModelProperty("会议详情")
    @TableField("detail")
    String detail;
}
