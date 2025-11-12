package com.briup.web.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/15-10-15-11:08
 * @Description：会话追踪技术：cookie
 * 第一次请求 响应的cookie和第二次请求携带的cookie是同一个
 */
@RestController
@RequestMapping("cookie")
public class CookieController {
    /**
     * 访问接口，产生cookie对象[Map存用户信息]并写入浏览器中
     * @return
     */
    @GetMapping("add")
    public String addCookie(HttpServletResponse response){
        // 账号信息 json对象字符串
        Cookie c = new Cookie("user", "tom");
//        Cookie c = new Cookie("username","admin");
//        Cookie c2 = new Cookie("password","123");
        response.addCookie(c);
//        response.addCookie(c2);
        return "添加cookie";
    }

    /**
     * 浏览器请求该接口时会携带cookie
     */
    @GetMapping("get")
    public String getCookie(HttpServletRequest request){
        // 取出cookie的username和password,存在就认为登录过
        Cookie[] cookies = request.getCookies();
        if(cookies!=null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                if(name.equals("user") && value.equals("tom")){
                    System.out.println("请求资源....");
                }
            }
        }
        return "获取cookie";
    }
}
