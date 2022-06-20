package com.example.pojo;


import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@lombok.Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "用户信息表")
public class Userinfo {

    @ApiModelProperty("用户id")
    @TableField("user_id")
    Integer userId;

    @ApiModelProperty("用户名")
    @TableField(exist = false)
    String username;

    @ApiModelProperty("密码")
    @TableField(exist = false)
    String password;

    @ApiModelProperty("用户角色")
    @TableField("user_role")
    String userRole;

    @ApiModelProperty("邮箱")
    @TableField("email")
    String email;

    @ApiModelProperty("用户名字")
    @TableField("name")
    String name;

    @ApiModelProperty("隶属机构")
    @TableField("administration")
    String administration;

    @ApiModelProperty("网址")
    @TableField("webpage")
    String webpage;

    @ApiModelProperty("地址")
    @TableField("address")
    String address;

    @ApiModelProperty("国家")
    @TableField("country")
    String country;

}
