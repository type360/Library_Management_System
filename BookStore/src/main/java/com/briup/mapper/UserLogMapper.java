package com.briup.mapper;

import com.briup.pojo.UserLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogMapper {
//插入一条日志
    void insert(UserLog log);
}
