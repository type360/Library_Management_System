package com.briup.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户实体
 */
@ApiModel("User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	@ApiModelProperty("主键ID")
	private Integer id;

	@ApiModelProperty("登录用户名")
	private String username;

	@ApiModelProperty("登录密码")
	private String password;

	@ApiModelProperty("手机号")
	private String phone;

	@ApiModelProperty("用户头像")
	private String avatar;

	@ApiModelProperty("用户性别(0男，1女)")
	private String gender;

	@ApiModelProperty("用户状态(0正常，1禁用)")
	private Short status;

	@ApiModelProperty("用户生日")
	private LocalDate birthday;

	@ApiModelProperty("注册时间")
	private LocalDateTime registerTime;

	@ApiModelProperty("角色编号 1. 管理员 2. 普通用户")
	private Short roleId;

	@ApiModelProperty("用户是否开通会员")
	private Boolean isVip;

	@ApiModelProperty("用户会员到期时间")
	private LocalDateTime vipExpirationTime;

	@ApiModelProperty("用户默认的收货地址")
	private Short defaultAddressId;
}
