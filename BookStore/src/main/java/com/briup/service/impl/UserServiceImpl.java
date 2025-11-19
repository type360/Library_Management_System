package com.briup.service.impl;

import com.briup.exception.ServiceException;
import com.briup.mapper.UserLogMapper;
import com.briup.mapper.UserMapper;
import com.briup.pojo.User;
import com.briup.pojo.UserLog;
import com.briup.pojo.dto.UserBaseDto;
import com.briup.pojo.dto.UserLogin;
import com.briup.pojo.dto.UserPageDto;
import com.briup.pojo.vo.UserPageVO;
import com.briup.response.ResultCode;
import com.briup.service.UserLogService;
import com.briup.service.UserService;
import com.briup.utils.BeanCopyUtils;
import com.briup.utils.JwtUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserLogMapper userLogMapper;
    @Resource
    private UserLogService userLogService;

    /**
     * 用户登录逻辑处理
     *
     * @param userLogin 用户登录对象 内含用户名及密码
     *                  后可添加验证码等数据
     * @return user
     */
    @Override
    public String login(UserLogin userLogin) {
        //判断用户名和密码是否存在值(参数校验)
        if (userLogin == null
                || !StringUtils.hasText(userLogin.getUsername())
                || !StringUtils.hasText(userLogin.getPassword())) {
            throw new ServiceException(ResultCode.PARAM_NOT_COMPLETE);
        }

        User user = userMapper.getUserByUsername(userLogin.getUsername());

        //判断用户是否存在及密码是否正确
        if (user == null || !userLogin.getPassword().equals(user.getPassword())) {
            throw new ServiceException(ResultCode.USER_LOGIN_ERROR);
        }

        //判断用户状态是否可用 用户状态(0正常，1禁用)
        if (user.getStatus() == 1) {
            throw new ServiceException(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }

        Map<String, Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getUsername());
        String token = JwtUtil.generateJwt(claims);
        return token;
    }

    /**
     * 分页多条件查询用户信息
     *
     * @param userPageDto 用于封装分页多条件查询用户时的条件参数
     */
    @Override
    public PageInfo<UserPageVO> getUsersPageByConditional(UserPageDto userPageDto) {
        //使用PageHelper开启分页,设置分页参数
        PageHelper.startPage(userPageDto.getPageNum(), userPageDto.getPageSize());

        //根据条件查询所有相关的用户
        List<User> userList = userMapper.getUserListByConditional(userPageDto);
        Page<User> page = (Page<User>) userList;

        //Bean拷贝
        List<UserPageVO> userPageVOS = BeanCopyUtils.copyBeanList(userList, UserPageVO.class);

        //封装在PageInfo对象中,并返回
        PageInfo<UserPageVO> pageInfo = new PageInfo<>(userPageVOS);
        //设置总条数
        pageInfo.setTotal(page.getTotal());

        return pageInfo;
    }

    @Override
    public void deleteUsers(List<Integer> ids) {
        userMapper.deleteUsers(ids);
    }

    /**
     * spring托管事务
     *   编程式事务 手动设置手动提交 提交事务 回滚事务
     *   声明式事务 注解@Transcation解决[本质上cglib动态代理]
     * Transactional 默认只回滚运行时异常
     * Propagation.REQUIRED 如果有事务，加入事务
     */
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    @Override
    public void addUser(UserBaseDto userBaseDto) throws Exception {
        // 省略校验 用户名不能为空 不能重复
        // 数据库和实体对应关系
        User user = BeanCopyUtils.copyBean(userBaseDto, User.class);
        //设置注册时间为当前时间
        user.setRegisterTime(LocalDateTime.now());
        //设置用户角色 默认为 2 普通用户
        user.setRoleId((short) 2);
        // 设置用户会员状态 默认为 false
        user.setIsVip(false);
        // 如果没有传性别,默认设置为 男
        if (user.getGender() == null) {
            user.setGender("0");
        }
        // 第一张表
        userMapper.addUser(user);
//        int i = 1/0;
        int i = 0;
        try {
            if(i == 0){
                throw new Exception("编译时异常");
            }
        }  finally {
            // 第二张表
            UserLog userLog = new UserLog();
            userLog.setMessage("添加用户:" + user.getUsername());
            userLog.setCreateTime(LocalDateTime.now());
            userLogService.addUserLog(userLog);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
