package com.briup.web.controller;

import com.briup.pojo.User;
import com.briup.pojo.dto.UserBaseDto;
import com.briup.pojo.dto.UserPageDto;
import com.briup.pojo.vo.UserPageVO;
import com.briup.response.Result;
import com.briup.service.UserService;
import com.briup.utils.JwtUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(tags = "用户管理模块接口")
@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("分页多条件查询用户信息")
    @GetMapping
    public Result<PageInfo<UserPageVO>> list(UserPageDto userPageDto) {
        log.info("分页多条件查询用户数据:{}", userPageDto);
//		log.debug("debug test");
//		log.warn("warn test");
//		log.error("error test");
        return Result.success(userService.getUsersPageByConditional(userPageDto));
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{ids}")
    public Result<String> deleteUsers(@PathVariable("ids") List<Integer> ids) {
        log.info("删除用户:{}", ids);
        userService.deleteUsers(ids);
        return Result.success();
    }

    @ApiOperation("新增用户")
    @PostMapping //UserBaseDao封装了常用属性， base中已提供
    // DTO 数据传输对象 接受参数
    // vo 视图对象 返回数据
    public Result<String> addUser(@RequestBody UserBaseDto userBaseDto) throws Exception {
        log.info("开始新增用户:{}", userBaseDto);
        userService.addUser(userBaseDto);
        return Result.success("新增成功");
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader("token") String token) {
        String username = JwtUtil.getUsername(token);
        User user = userService.getUserByUsername(username);
        user.setPassword(null);
        return Result.success(user);
    }

}
