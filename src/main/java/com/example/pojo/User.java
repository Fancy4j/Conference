package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户表")
public class User {

    @ApiModelProperty("用户id")
    @TableField("user_id")
    Integer userId;

    @ApiModelProperty("用户名")
    @TableField("username")
    String username;

    @ApiModelProperty("密码")
    @TableField("password")
    String password;

    @ApiModelProperty("用户角色")
    @TableField("user_role")
    String userRole;

}
