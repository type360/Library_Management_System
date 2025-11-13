package com.briup.web.controller;

import com.briup.exception.ServiceException;
import com.briup.response.ResultCode;
import com.briup.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: vanse(lc)
 * @Date: 2025/10/15-10-15-15:07
 * @Description：com.briup.web.controller
 */
@RestController
@RequestMapping("token")
public class TokenController {
   @GetMapping("add")
    public String addToken() {
//       return UUID.randomUUID().toString();
       Map<String, Object> claims = new HashMap<>();
       claims.put("userId", 1);
       String token = JwtUtil.generateJwt(claims).toString();
       return token;
   }
   @GetMapping("get")
    public String getToken(HttpServletRequest request) {
       String token = request.getHeader("token");
       if (!StringUtils.hasText(token)) {
           return "未登录";
       }
       //可能过期 可能篡改
       try {
           Claims claims = JwtUtil.parseJWT(token);//解密token获取载荷内容
           Object o = claims.get("userId");
           System.out.println("o = "+ o);
       } catch (Exception e) {
           throw new ServiceException(ResultCode.TOKEN_INVALID);//在自定义异常中可以自己写一个
//           return "登陆失败";
       }
       return "请求成功";
   }
}
//我不能进入一个服务集群后在每个服务中都做一次校验，所以我们要把这个功能添加到全局的拦截器那里。