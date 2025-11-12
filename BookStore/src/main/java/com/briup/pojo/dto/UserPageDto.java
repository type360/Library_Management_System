package com.briup.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("分页多条件获取用户信息的条件实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageDto implements Serializable {

	@ApiModelProperty("登录用户名")
	private String username;

	@ApiModelProperty("用户状态(0正常，1禁用)")
	private Short status;

	@ApiModelProperty("开启时间(用户注册时间区间)")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime startTime;

	@ApiModelProperty("终止时间(用户注册时间区间)")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime endTime;

	@ApiModelProperty("当前页码")
	private Integer pageNum;

	@ApiModelProperty("页面大小")
	private Integer pageSize;
}
