package com.briup.web.controller;

import com.briup.pojo.User;
import com.briup.pojo.dto.UserLogin;
import com.briup.response.Result;
import com.briup.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@Api(tags = "用户登录接口")
@RestController
public class AuthController {

	@Resource
	private UserService userService;

	@ApiOperation("用户登录")
	@PostMapping("/login")
	public Result<User> login(@RequestBody UserLogin user) {
		log.info("用户登录:{}", user);
		return Result.success(userService.login(user));
	}

	@ApiOperation("退出登录")
	@PostMapping("/logout")
	public Result<String> logout() {
		return Result.success();
	}
}
