package com.briup.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("用户登录实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {
	private String username;
	private String password;
}
