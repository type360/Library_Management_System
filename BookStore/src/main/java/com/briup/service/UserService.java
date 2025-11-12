package com.briup.service;

import com.briup.pojo.User;
import com.briup.pojo.dto.UserBaseDto;
import com.briup.pojo.dto.UserLogin;
import com.briup.pojo.dto.UserPageDto;
import com.briup.pojo.vo.UserPageVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
	/**
	 * @param user 登录用户参数对象
	 * @return user
	 */
	User login(UserLogin user);

	/**
	 * 分页多条件查询用户信息
	 *
	 * @param userPageDto 用于封装分页多条件查询用户时的条件参数
	 */
	PageInfo<UserPageVO> getUsersPageByConditional(UserPageDto userPageDto);


	/**
	 * 批量删除用户信息
	 * @param ids 用户ids
	 */
	void deleteUsers(List<Integer> ids);

    void addUser(UserBaseDto userBaseDto);
}
