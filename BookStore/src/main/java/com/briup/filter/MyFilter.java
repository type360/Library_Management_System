package com.briup.filter;

import com.briup.utils.JwtUtil;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 86151
 * @program: Library_Management_System
 * @description:哪些资源需要登录
 * @create 2025/11/13 14:47
 **/
@WebFilter("/*")
public class MyFilter implements Filter {
    //访问任何请求之前都会先请求该方法
    //该方法必须放行，才能请求资源
    /*
    @param servletRequest 请求
    @param servletResponse  响应
    @param filterChain 过滤器链
    */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setContentType("text/plain;charset=utf-8");
        //查看访问的目标资源
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        //没有放行 资源无法访问
        String token = req.getHeader("token");
        System.out.println("token = " + token);
        if (!StringUtils.hasText(token)) {
            resp.getWriter().write("请登录");
        }
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            resp.getWriter().write("令牌失效");
        }
        //放行资源
        filterChain.doFilter(req, resp);

    }
}
