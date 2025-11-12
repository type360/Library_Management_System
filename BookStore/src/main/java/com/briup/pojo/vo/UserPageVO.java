package com.briup.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@ApiModel("分页多条件获取用户信息接口模型数据")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserPageVO implements Serializable {
	/**
	 * 主键ID
	 */
	@ApiModelProperty("主键ID")
	private Integer id;

	/**
	 * 登录用户名
	 */
	@ApiModelProperty("登录用户名")
	private String username;


	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String phone;

	/**
	 * 用户头像
	 */
	@ApiModelProperty("用户头像")
	private String avatar;

	/**
	 * 用户性别(0男，1女)
	 */
	@ApiModelProperty("用户性别(0男，1女)")
	private String gender;

	/**
	 * 用户状态(0正常，1禁用)
	 */
	@ApiModelProperty("用户状态(0正常，1禁用)")
	private Short status;

	/**
	 * 用户生日
	 */
	@ApiModelProperty("用户生日")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthday;

	/**
	 * 注册时间
	 */
	@ApiModelProperty("注册时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private LocalDateTime registerTime;

	/**
	 * 角色编号 1. 管理员 2. 普通用户
	 */
	@ApiModelProperty("角色编号 1. 管理员 2. 普通用户")
	private Short roleId;
}
