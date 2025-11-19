package com.briup.web.interceptor;

import com.briup.exception.ServiceException;
import com.briup.response.ResultCode;
import com.briup.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/16-10-16-14:17
 * 1.创建拦截器 拦截token
 * 2.配置拦截器【springmvc】
 */
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    /**
     * 目标方法之前执行
     * @return true,放行资源 false 不放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // 放行预检请求
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())){ return true; }

        // 获取请求头中的token值
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            throw new ServiceException(ResultCode.USER_NOT_LOGIN);
        }
        // token非法
        try {
//            Claims claims = JwtUtil.parseJWT(token);
            Integer userId = JwtUtil.getUserId(token);
            System.out.println("userId = " + userId);
        }catch (Exception e){
            throw new ServiceException(ResultCode.TOKEN_INVALID);
        }
        // 具体认证规则
        return true;
    }

    /**
     * 目标方法之后执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 渲染视图后执行
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
