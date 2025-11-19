package com.briup.service.impl;

import com.briup.mapper.UserLogMapper;
import com.briup.pojo.UserLog;
import com.briup.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addUserLog(UserLog userLog) throws Exception {
        userLogMapper.insert(userLog);
    }
}
