package com.briup.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/15-10-15-14:14
 * @Description：会话追踪技术 session
 * 第一次请求的session和第二次获取的session必须是同一个
 * request 一次请求 【刷新一下就是一次请求】
 * session 一次会话【同一个浏览器在访问状态】
 *   浏览器发送n次请求 服务器能识别是同一个session，不会创建第二个session
 *
 *原理：
 *  第一次发送请求request.getSession() 创建了session对象，
 *      将JSESSSIONID:1B0D02D5519B2442721BD6E12437C17D,存到cookie
 *  第二次发送请求request.getSession(),会携带cookie中JSESSIONID,
 *      不会再创建session对象，共享session对象
 */
@RestController
@RequestMapping("session")
public class SessionController {
    @GetMapping("add")
    public String addSession(HttpServletRequest request){ // tomcat自动创建session
        HttpSession session = request.getSession();
        // 登录成功 将用户存到session中
        session.setAttribute("user","tom");
        System.out.println("add: " + session.getId());
        return "添加会话";
    }
    @GetMapping("get")
    public String getSession(HttpServletRequest request){ // tomcat自动创建session
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            System.out.println("get: " + session.getId());
            System.out.println("访问资源");
        }else{
            return "未登录";
        }
        return "获取会话";
    }
}
