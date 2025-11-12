package com.briup.mapper;

import com.briup.pojo.User;
import com.briup.pojo.dto.UserPageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {

	User getUserByUsername(String username);


	List<User> getUserListByConditional(UserPageDto userPageDto);

	void deleteUsers(List<Integer> ids);

	void addUser(User user);

}
