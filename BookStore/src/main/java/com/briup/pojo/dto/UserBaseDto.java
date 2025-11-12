package com.briup.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@ApiModel("用户常用属性实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBaseDto implements Serializable {

	@ApiModelProperty("id,更新时使用")
	public Integer id;

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("密码,添加用户时使用")
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
}
